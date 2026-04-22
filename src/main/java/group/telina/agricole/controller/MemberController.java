package group.telina.agricole.controller;

import group.telina.agricole.dto.MemberRefereeRest;
import group.telina.agricole.dto.MemberRest;
import group.telina.agricole.entity.Member;
import group.telina.agricole.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<?> createMembers(@RequestBody List<Member> membersToCreate) {
        try {
            List<Member> createdMembers = memberService.admitAll(membersToCreate);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdMembers.stream()
                            .map(member -> {
                                List<MemberRefereeRest> refereesRest = member.getReferees().stream()
                                        .map(ref -> new MemberRefereeRest(
                                                ref.getId(),
                                                ref.getFirstName() + " " + ref.getLastName(),
                                                ref.getOccupation()
                                        ))
                                        .toList();

                                return new MemberRest(
                                        member.getId(),
                                        member.getFirstName(),
                                        member.getLastName(),
                                        member.getEmail(),
                                        member.getOccupation(),
                                        member.getPhoneNumber(),
                                        refereesRest
                                );
                            }).toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}