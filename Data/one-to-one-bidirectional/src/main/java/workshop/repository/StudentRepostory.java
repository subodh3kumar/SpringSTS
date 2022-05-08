package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import workshop.entity.Student;

@Repository
public interface StudentRepostory extends JpaRepository<Student, Long> {

}
