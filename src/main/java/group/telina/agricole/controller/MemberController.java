package group.telina.agricole.controller;

import group.telina.agricole.entity.Member;
import group.telina.agricole.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member m) {
        return ResponseEntity
                .status(201)
                .body(service.create(m));
    }
}