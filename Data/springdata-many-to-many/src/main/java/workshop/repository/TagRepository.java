package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import workshop.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
