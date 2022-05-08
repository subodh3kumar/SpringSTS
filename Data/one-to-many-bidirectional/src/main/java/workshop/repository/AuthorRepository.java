package workshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import workshop.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
