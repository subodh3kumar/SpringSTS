package workshop;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Employee;
import workshop.entity.EmployeeIdentity;
import workshop.repository.EmployeeRepository;

@Slf4j
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CompositeKeyTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	@Order(1)
	public void create() {
		log.info("create() method called");

		Employee subodh = new Employee("SUBODH");
		EmployeeIdentity subodhIdentity = new EmployeeIdentity("E-100", "O-100");
		subodh.setId(subodhIdentity);

		Employee juli = new Employee("JULI");
		EmployeeIdentity juliIdentity = new EmployeeIdentity("E-101", "O-101");
		juli.setId(juliIdentity);

		repository.save(subodh);
		repository.save(juli);
		
		log.info("entity saved successfully");
	}

	@Test
	@Order(2)
	public void read() {
		log.info("read() method called");

		EmployeeIdentity subodhIdentity = new EmployeeIdentity("E-100", "O-100");
		EmployeeIdentity juliIdentity = new EmployeeIdentity("E-101", "O-101");

		final Optional<Employee> subodh = repository.findById(subodhIdentity);
		final Optional<Employee> juli = repository.findById(juliIdentity);

		subodh.ifPresent(emp -> log.info(emp.toString()));
		juli.ifPresent(emp -> log.info(emp.toString()));
	}
}
