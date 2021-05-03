package backend.nomad.repository;

import backend.nomad.domain.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final EntityManager em;

    public void save(Group group) {
        em.persist(group);
    }


}
