package workshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Post;
import workshop.entity.Tag;
import workshop.repository.PostRepository;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ManyToManyMappingTest {

	@Autowired
	private PostRepository postRepository;

	@Test
	public void create() throws Exception {
		Post hibernatePost = new Post();
		hibernatePost.setTitle("Hibernate Mapping");
		hibernatePost.setContent("Hibernate JPA Many-to-Many Mapping");
		hibernatePost.setPostedAt(new Date());

		Post springbootPost = new Post();
		springbootPost.setTitle("Springboot Ddata");
		springbootPost.setContent("Springboot JPA Many-to-Many Mapping");
		springbootPost.setPostedAt(new Date());

		Tag springbootTag = new Tag("springboot");
		Tag hibernateTag = new Tag("hibernate");

		hibernatePost.getTags().add(hibernateTag);
		springbootPost.getTags().add(springbootTag);

		springbootTag.getPosts().add(springbootPost);
		hibernateTag.getPosts().add(hibernatePost);

		postRepository.save(hibernatePost);
		postRepository.save(springbootPost);

		assertNotNull(springbootPost);
		assertNotNull(springbootTag);
		assertNotNull(hibernatePost);
		assertNotNull(hibernateTag);

		log.info(hibernatePost.toString());
		log.info(hibernateTag.toString());
		log.info(springbootPost.toString());
		log.info(springbootTag.toString());
	}
}
