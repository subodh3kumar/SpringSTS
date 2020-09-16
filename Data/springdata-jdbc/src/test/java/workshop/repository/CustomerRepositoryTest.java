package workshop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Address;
import workshop.entity.Customer;
import workshop.entity.Gender;

@Slf4j
@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	@DisplayName("create customer")
	public void create() {
		log.info("create customer");

		Customer customer = new Customer();
		customer.setName("subodh");
		customer.setSex(Gender.MALE);

		Address address = new Address();
		address.setCity("chennai");
		customer.getAddresses().put("current", address);

		Customer save = repository.save(customer);
		log.info(save.toString());
		assertThat(save).isNotNull();
	}
}
