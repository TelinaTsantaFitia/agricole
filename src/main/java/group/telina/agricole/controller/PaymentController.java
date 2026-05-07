package group.telina.agricole.controller;

import group.telina.agricole.entity.Payment;
import group.telina.agricole.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment pay(@RequestBody Payment p) {
        return paymentService.pay(p);
    }
}