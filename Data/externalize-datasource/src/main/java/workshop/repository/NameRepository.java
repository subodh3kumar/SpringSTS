package workshop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import workshop.entity.NameEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class NameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public NameRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void load() {
        log.info("load() method start");

        final String query = "SELECT name FROM NameEntity name";
        List<NameEntity> names = entityManager.createQuery(query, NameEntity.class).getResultList();
        names.forEach(name -> log.info(name.toString()));

        entityManager.close();
    }
}
