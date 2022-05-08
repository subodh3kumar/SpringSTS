package workshop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import workshop.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@DataJpaTest
@ContextConfiguration(classes = MainApplication.class)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
class EmployeeEntityTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void display() {
    log.info("display() method start");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Employee> employees = entityManager.createQuery("SELECT emp from Employee emp", Employee.class).getResultList();
        transaction.commit();
        employees.forEach(employee -> log.info(employee.toString()));
    }
}
