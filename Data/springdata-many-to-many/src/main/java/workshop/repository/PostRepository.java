package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import workshop.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
