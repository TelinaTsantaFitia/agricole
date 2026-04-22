package group.telina.agricole.service;

import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.entity.Member;
import group.telina.agricole.repository.CollectivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectivityService {
    private final CollectivityRepository repository;

    public CollectivityService(CollectivityRepository repository) {
        this.repository = repository;
    }

    public List<Collectivity> saveAll(List<Collectivity> collectivities) {
        return collectivities.stream()
                .map(repository::save)
                .toList();
    }
}
