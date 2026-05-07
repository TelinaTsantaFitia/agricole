package group.telina.agricole.service;

import group.telina.agricole.dto.CollectivityRest;
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

    // POST /collectivities
    public CollectivityRest create(Collectivity c) {
        Collectivity saved = repository.save(c);
        return toRest(saved, List.of());
    }

    // GET /collectivities
    public List<CollectivityRest> getAll() {
        return repository.findAll()
                .stream()
                .map(c -> toRest(c, List.of()))
                .toList();
    }

    // GET /collectivities/{id}
    public CollectivityRest getById(String id) {
        Collectivity c = repository.findById(id);

        if (c == null) {
            throw new RuntimeException("Collectivity not found: " + id);
        }

        List<Member> members = repository.findMembersByCollectivityId(id);
        return toRest(c, members);
    }

    // Méthode utilitaire
    private CollectivityRest toRest(Collectivity c, List<Member> members) {
        return new CollectivityRest(
                c.getId(),
                c.getNumber(),
                c.getName(),
                c.getAddress(),
                c.getCollectivityType(),
                members
        );
    }

    // PUT /collectivities/{id}/informations
    public CollectivityRest updateInformations(String id, Collectivity c) {

        if (repository.findById(id) == null) {
            throw new RuntimeException("Collectivity not found with id: " + id);
        }

        // Vérification unicité du number
        if (repository.existsByNumberAndIdNot(c.getNumber(), id)) {
            throw new RuntimeException("Number already assigned");
        }

        repository.updateInformations(id, c.getNumber(), c.getName());

        // Recharger et retourner l'entité mise à jour
        return getById(id);
    }
}