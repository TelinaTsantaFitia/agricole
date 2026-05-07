package group.telina.agricole.controller;

import group.telina.agricole.dto.CollectivityRest;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.entity.FinancialAccount;
import group.telina.agricole.service.CollectivityService;
import group.telina.agricole.service.FinancialAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService service;
    private final FinancialAccountService financialAccountService;

    public CollectivityController(CollectivityService service,
                                  FinancialAccountService financialAccountService) {
        this.service = service;
        this.financialAccountService = financialAccountService;
    }

    // POST /collectivities
    @PostMapping
    public ResponseEntity<CollectivityRest> create(@RequestBody Collectivity c) {
        return ResponseEntity.status(201).body(service.create(c));
    }

    // GET /collectivities
    @GetMapping
    public ResponseEntity<List<CollectivityRest>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET /collectivities/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CollectivityRest> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET /collectivities/{id}/financialAccounts?at=2026-01-01
    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam LocalDate at) {
        return ResponseEntity.ok(financialAccountService.getBalances(id, at));
    }

    // PUT /collectivities/{id}/informations
    @PutMapping("/{id}/informations")
    public ResponseEntity<CollectivityRest> updateInformations(
            @PathVariable String id,
            @RequestBody Collectivity c) {
        return ResponseEntity.ok(service.updateInformations(id, c));
    }
}