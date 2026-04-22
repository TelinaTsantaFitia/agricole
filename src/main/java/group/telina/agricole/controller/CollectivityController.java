package group.telina.agricole.controller;

import group.telina.agricole.dto.CollectivityRest;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.service.CollectivityService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<List<CollectivityRest>> create(
            @RequestBody List<Collectivity> collectivities
    ) {
        List<Collectivity> saved = service.saveAll(collectivities);

        List<CollectivityRest> response = saved.stream()
                .map(c -> new CollectivityRest(
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getCollectivityType(),
                        c.getEmail(),
                        c.getPhoneNumber()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}