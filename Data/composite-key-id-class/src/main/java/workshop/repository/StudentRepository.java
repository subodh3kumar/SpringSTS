package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.entity.StudentEntity;
import workshop.entity.StudentId;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, StudentId> {
}
