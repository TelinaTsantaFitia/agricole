package group.telina.agricole.controller;

import group.telina.agricole.dto.CollectivityRest;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService service;

    public CollectivityController(CollectivityService service) {
        this.service = service;
    }

    // POST /collectivities
    @PostMapping
    public ResponseEntity<CollectivityRest> create(
            @RequestBody Collectivity c
    ) {
        return ResponseEntity
                .status(201)
                .body(service.create(c));
    }

    // GET /collectivities
    @GetMapping
    public ResponseEntity<List<CollectivityRest>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}