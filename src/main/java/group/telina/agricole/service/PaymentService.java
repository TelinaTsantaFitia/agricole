package group.telina.agricole.service;

import group.telina.agricole.entity.Account;
import group.telina.agricole.entity.Payment;
import group.telina.agricole.repository.AccountRepository;
import group.telina.agricole.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          AccountRepository accountRepository) {
        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
    }

    public Payment pay(Payment p) {

        // 1. sauvegarde paiement
        Payment saved = paymentRepository.save(p);

        // 2. récupération compte
        Account account = accountRepository.findById(p.getAccountId());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        // 3. calcul nouveau solde
        double newBalance = account.getBalance() + p.getAmount();

        // 4. update DB
        accountRepository.updateBalance(account.getId(), newBalance);

        return saved;
    }
}