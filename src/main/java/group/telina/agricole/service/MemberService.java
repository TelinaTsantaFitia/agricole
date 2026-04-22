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
        return members.stream()
                .map(memberRepository::save)
                .toList();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}