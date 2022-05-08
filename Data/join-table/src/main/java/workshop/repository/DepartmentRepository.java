package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import workshop.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

}
