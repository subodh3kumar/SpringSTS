package workshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import workshop.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
