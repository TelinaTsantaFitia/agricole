package group.telina.agricole.controller;

import group.telina.agricole.entity.Member;
import group.telina.agricole.entity.Payment;
import group.telina.agricole.service.MemberService;
import group.telina.agricole.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;
    private final PaymentService paymentService;

    public MemberController(MemberService service, PaymentService paymentService) {
        this.service = service;
        this.paymentService = paymentService;
    }

    // POST /members
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member m) {
        return ResponseEntity
                .status(201)
                .body(service.create(m));
    }

    // POST /members/{id}/payments
    @PostMapping("/{id}/payments")
    public ResponseEntity<Payment> pay(
            @PathVariable String id,
            @RequestBody Payment p) {
        p.setMemberId(id);
        return ResponseEntity
                .status(201)
                .body(paymentService.pay(p));
    }
}