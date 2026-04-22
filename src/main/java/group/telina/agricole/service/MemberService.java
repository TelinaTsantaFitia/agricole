package group.telina.agricole.service;

import group.telina.agricole.entity.Member;
import group.telina.agricole.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> admitAll(List<Member> members) {
        return members.stream().map(this::validateAndSave).toList();
    }

    private Member validateAndSave(Member member) {
        // Règle B-2 : Au moins 2 parrains
        if (member.getReferees() == null || member.getReferees().size() < 2) {
            throw new RuntimeException("Un nouveau membre doit avoir au moins 2 parrains.");
        }

        int localCount = 0;
        int externalCount = 0;

        for (Member ref : member.getReferees()) {
            Member sponsor = memberRepository.findById(ref.getId());
            if (sponsor == null) throw new RuntimeException("Parrain " + ref.getId() + " introuvable.");

            if (sponsor.getCollectivityId().equals(member.getCollectivityId())) {
                localCount++;
            } else {
                externalCount++;
            }
        }

        // Règle B-2 : Majorité locale
        if (localCount < externalCount) {
            throw new RuntimeException("Parrainage invalide : les parrains locaux doivent être majoritaires.");
        }

        return memberRepository.save(member);
    }
}