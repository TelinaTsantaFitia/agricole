package group.telina.agricole.service;

import group.telina.agricole.entity.Member;
import group.telina.agricole.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member create(Member m) {

        // ❌ règle 1 : minimum 2 sponsors
        if (m.getSponsors() == null || m.getSponsors().size() < 2) {
            throw new RuntimeException("Minimum 2 sponsors required");
        }

        // ❌ règle 2 : comptage par collectivité
        long same = m.getSponsors()
                .stream()
                .filter(s -> s.getCollectivityId()
                        .equals(m.getCollectivityId()))
                .count();

        long others = m.getSponsors().size() - same;

        // ❌ règle B-2
        if (same < others) {
            throw new RuntimeException("Invalid sponsorship rule");
        }

        return repository.save(m);
    }
}