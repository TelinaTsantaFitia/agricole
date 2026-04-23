package group.telina.agricole.controller;

import group.telina.agricole.dto.CollectivityRest;
import group.telina.agricole.entity.Account;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.service.CollectivityService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService service;

    public CollectivityController(
            CollectivityService service
    ) {
        this.service = service;
    }


    // EXISTANT
    @GetMapping
    public List<CollectivityRest> getAll(){
        return service.getAll();
    }



    // ===================================
    // GET /collectivities/{id}
    // ===================================
    @GetMapping("/{id}")
    public Collectivity getById(
            @PathVariable String id
    ){
        return service.getById(id);
    }



    // ==============================================
    // GET /collectivities/{id}/financialAccounts
    // ==============================================
    @GetMapping("/{id}/financialAccounts")
    public List<Account> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam String at
    ){
        return service.getFinancialAccounts(
                id,
                at
        );
    }

}