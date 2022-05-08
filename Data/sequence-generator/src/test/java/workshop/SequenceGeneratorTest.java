package workshop;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.User;
import workshop.repository.UserRepository;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(OrderAnnotation.class)
public class SequenceGeneratorTest {

	@Autowired
	private UserRepository repository;

	@Test
	@Order(1)
	public void create() {
		log.info("create() method called");
		User subodh = new User("SUBODH");
		User juli = new User("JULI");
		User akash = new User("AKASH");
		User harsh = new User("HARSH");

		List<User> entities = new ArrayList<>();
		entities.add(subodh);
		entities.add(juli);
		entities.add(akash);
		entities.add(harsh);

		repository.saveAll(entities);
		repository.flush();
		log.info("users saved succ");
	}

	@Test
	@Order(2)
	public void read() {
		log.info("read() method called");
		List<User> entities = repository.findAll();
		entities.forEach(System.out::println);
	}
}
