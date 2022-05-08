package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import workshop.entity.Employee;
import workshop.entity.EmployeeIdentity;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {

}
