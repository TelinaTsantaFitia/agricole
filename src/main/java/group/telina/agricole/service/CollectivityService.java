package group.telina.agricole.service;

import group.telina.agricole.dto.*;
import group.telina.agricole.entity.*;
import group.telina.agricole.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectivityService {

    private final CollectivityRepository repository;
    private final MemberRepository memberRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final PaymentRepository paymentRepository;
    private final ActivityRepository activityRepository;
    private final AttendanceRepository attendanceRepository;

    public CollectivityService(
            CollectivityRepository repository,
            MemberRepository memberRepository,
            MembershipFeeRepository membershipFeeRepository,
            PaymentRepository paymentRepository,
            ActivityRepository activityRepository,
            AttendanceRepository attendanceRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.paymentRepository = paymentRepository;
        this.activityRepository = activityRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public CollectivityRest create(Collectivity c) {
        Collectivity saved = repository.save(c);
        return toRest(saved, List.of());
    }

    public List<CollectivityRest> getAll() {
        return repository.findAll()
                .stream()
                .map(c -> toRest(c, List.of()))
                .toList();
    }

    public CollectivityRest getById(String id) {
        Collectivity c = repository.findById(id);
        if (c == null) {
            throw new RuntimeException("Collectivity not found: " + id);
        }
        List<Member> members = repository.findMembersByCollectivityId(id);
        return toRest(c, members);
    }

    public CollectivityRest updateInformations(String id, Collectivity c) {
        if (repository.findById(id) == null) {
            throw new RuntimeException("Collectivity not found with id: " + id);
        }
        if (repository.existsByNumberAndIdNot(c.getNumber(), id)) {
            throw new RuntimeException("Number already assigned");
        }
        repository.updateInformations(id, c.getNumber(), c.getName());
        return getById(id);
    }

    private CollectivityRest toRest(Collectivity c, List<Member> members) {
        return new CollectivityRest(
                c.getId(),
                c.getNumber(),
                c.getName(),
                c.getAddress(),
                c.getCollectivityType(),
                members
        );
    }

    public CollectivityStatRest getCollectivityStatistics(
            String collectivityId, LocalDate from, LocalDate to) {

        List<Member> members = memberRepository.findByCollectivityId(collectivityId);
        List<MembershipFee> activeFees = membershipFeeRepository
                .findByCollectivityIdAndStatus(collectivityId, "ACTIVE");
        List<Payment> payments = paymentRepository
                .findByCollectivityIdAndPaymentDateBetween(collectivityId, from, to);
        List<Activity> mandatoryActivities = activityRepository
                .findByCollectivityIdAndDateBetween(collectivityId, from, to);
        int totalActivities = mandatoryActivities.size();

        Map<String, BigDecimal> paidByMember = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getMemberId,
                        Collectors.reducing(BigDecimal.ZERO,
                                p -> BigDecimal.valueOf(p.getAmount()),
                                BigDecimal::add)
                ));

        BigDecimal totalDue = activeFees.stream()
                .map(MembershipFee::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<MemberStatRest> memberStats = members.stream().map(member -> {
            BigDecimal paid = paidByMember.getOrDefault(member.getId(), BigDecimal.ZERO);
            BigDecimal unpaid = totalDue.subtract(paid);
            if (unpaid.compareTo(BigDecimal.ZERO) < 0) unpaid = BigDecimal.ZERO;

            BigDecimal attendanceRate = BigDecimal.ZERO;
            if (totalActivities > 0) {
                long present = mandatoryActivities.stream()
                        .filter(a -> attendanceRepository
                                .countByActivityIdAndMemberIdAndPresent(a.getId(), member.getId()) > 0)
                        .count();
                attendanceRate = BigDecimal.valueOf(present * 100)
                        .divide(BigDecimal.valueOf(totalActivities), 2, RoundingMode.HALF_UP);
            }

            return new MemberStatRest(member.getId(), member.getFirstName(),
                    member.getLastName(), paid, unpaid, attendanceRate);
        }).collect(Collectors.toList());

        return new CollectivityStatRest(collectivityId, memberStats);
    }

    public List<FederationCollectivityStatRest> getFederationStatistics(
            LocalDate from, LocalDate to) {

        List<Collectivity> collectivities = repository.findAll();

        return collectivities.stream().map(col -> {
            List<Member> members = memberRepository.findByCollectivityId(col.getId());
            int totalMembers = members.size();

            List<MembershipFee> activeFees = membershipFeeRepository
                    .findByCollectivityIdAndStatus(col.getId(), "ACTIVE");

            BigDecimal totalDue = activeFees.stream()
                    .map(MembershipFee::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<Payment> payments = paymentRepository
                    .findByCollectivityIdAndPaymentDateBetween(col.getId(), from, to);

            Map<String, BigDecimal> paidByMember = payments.stream()
                    .collect(Collectors.groupingBy(
                            Payment::getMemberId,
                            Collectors.reducing(BigDecimal.ZERO,
                                    p -> BigDecimal.valueOf(p.getAmount()),
                                    BigDecimal::add)
                    ));

            long upToDate = members.stream()
                    .filter(m -> paidByMember.getOrDefault(m.getId(), BigDecimal.ZERO)
                            .compareTo(totalDue) >= 0)
                    .count();

            BigDecimal percentage = totalMembers == 0 ? BigDecimal.ZERO :
                    BigDecimal.valueOf(upToDate * 100)
                            .divide(BigDecimal.valueOf(totalMembers), 2, RoundingMode.HALF_UP);

            long newMembers = members.stream()
                    .filter(m -> m.getAdmissionDate() != null
                            && !m.getAdmissionDate().isBefore(from)
                            && !m.getAdmissionDate().isAfter(to))
                    .count();

            List<Activity> mandatoryActivities = activityRepository
                    .findByCollectivityIdAndDateBetween(col.getId(), from, to);
            int totalActivities = mandatoryActivities.size();

            BigDecimal attendanceRate = BigDecimal.ZERO;
            if (totalActivities > 0 && totalMembers > 0) {
                long totalPresences = mandatoryActivities.stream()
                        .flatMap(a -> members.stream()
                                .filter(m -> attendanceRepository
                                        .countByActivityIdAndMemberIdAndPresent(a.getId(), m.getId()) > 0))
                        .count();
                attendanceRate = BigDecimal.valueOf(totalPresences * 100)
                        .divide(BigDecimal.valueOf((long) totalActivities * totalMembers), 2, RoundingMode.HALF_UP);
            }

            return new FederationCollectivityStatRest(
                    col.getId(), col.getName(), percentage, newMembers, attendanceRate);

        }).collect(Collectors.toList());
    }
}