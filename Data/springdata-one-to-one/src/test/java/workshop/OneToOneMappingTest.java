package workshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Gender;
import workshop.entity.Student;
import workshop.entity.StudentProfile;
import workshop.repository.StudentRepostory;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OneToOneMappingTest {

	@Autowired
	private StudentRepostory studentRepostory;

	@Test
	public void create() throws Exception {
		Student subodh = new Student();
		subodh.setName("subodh");
		subodh.setEmail("subodh@gmail.com");

		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Chennai, TN");
		studentProfile.setGender(Gender.MALE);
		studentProfile.setDob(LocalDate.of(1988, 02, 21));

		subodh.setStudentProfile(studentProfile);
		studentProfile.setStudent(subodh);

		studentRepostory.save(subodh);

		assertNotNull(subodh);
		assertNotNull(studentProfile);

		log.info(subodh.toString());
		log.info(studentProfile.toString());

		Thread.sleep(10000);
	}
}
