package group.telina.agricole.service;

import group.telina.agricole.dto.CollectivityRest;
import group.telina.agricole.entity.Account;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.repository.CollectivityRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectivityService {

    private final CollectivityRepository repository;

    public CollectivityService(
            CollectivityRepository repository
    ) {
        this.repository = repository;
    }



    // POST
    public CollectivityRest create(
            Collectivity c
    ){

        boolean exists=
                repository.findAll()
                        .stream()
                        .anyMatch(x ->
                                x.getName()
                                        .equalsIgnoreCase(
                                                c.getName()
                                        ));

        if(exists){
            throw new RuntimeException(
                    "Collectivity already exists"
            );
        }

        Collectivity saved=
                repository.save(c);

        return new CollectivityRest(
                saved.getId(),
                saved.getNumber(),
                saved.getName(),
                saved.getAddress(),
                saved.getCollectivityType()
        );
    }



    // GET all
    public List<CollectivityRest> getAll(){

        return repository.findAll()
                .stream()
                .map(c -> new CollectivityRest(
                        c.getId(),
                        c.getNumber(),
                        c.getName(),
                        c.getAddress(),
                        c.getCollectivityType()
                ))
                .toList();
    }



    // ==========================
    // GET collectivity by id
    // ==========================
    public Collectivity getById(
            String id
    ){

        Collectivity c =
                repository.findById(id);

        if(c==null){
            throw new RuntimeException(
                    "Collectivity not found"
            );
        }

        return c;
    }




    // ===================================
    // GET financial accounts
    // ===================================
    public List<Account> getFinancialAccounts(
            String collectivityId,
            String at
    ){

        return repository.findFinancialAccounts(
                collectivityId,
                at
        );
    }

}