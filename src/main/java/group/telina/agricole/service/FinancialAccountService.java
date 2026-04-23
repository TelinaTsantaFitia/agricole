package group.telina.agricole.service;

import group.telina.agricole.entity.FinancialAccount;
import group.telina.agricole.repository.CollectivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@   Service
public class FinancialAccountService {

    private final CollectivityRepository repository;

    public FinancialAccountService(
            CollectivityRepository repository
    ){
        this.repository = repository;
    }

    public List<FinancialAccount> getBalances(
            String collectivityId,
            LocalDate at
    ){
        return repository.findFinancialAccounts(
                collectivityId,
                at
        );
    }

}