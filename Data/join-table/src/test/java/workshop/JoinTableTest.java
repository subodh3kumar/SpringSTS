package workshop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
import workshop.entity.DepartmentEntity;
import workshop.entity.Student;
import workshop.entity.StudentEntity;
import workshop.repository.DepartmentRepository;
import workshop.repository.StudentRepository;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(OrderAnnotation.class)
class JoinTableTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EntityManagerFactory managerFactory;

	@Test
	@Order(1)
	public void create() {
		log.info("create() method called");

		StudentEntity subodh = new StudentEntity("subodh", 1001, 32);
		StudentEntity juli = new StudentEntity("juli", 1003, 22);
		StudentEntity akash = new StudentEntity("akash", 1002, 20);
		StudentEntity moni = new StudentEntity("moni", 1003, 25);

		List<StudentEntity> students = new ArrayList<>();
		students.add(subodh);
		students.add(juli);
		students.add(akash);
		students.add(moni);

		studentRepository.saveAll(students);
		log.info("students saved successfully");

		DepartmentEntity dept1 = new DepartmentEntity(1001, "Computer Science");
		DepartmentEntity dept2 = new DepartmentEntity(1002, "Mathematics");
		DepartmentEntity dept3 = new DepartmentEntity(1003, "Home Science");

		List<DepartmentEntity> departments = new ArrayList<>();
		departments.add(dept1);
		departments.add(dept2);
		departments.add(dept3);

		departmentRepository.saveAll(departments);
		log.info("departments saved successfully");
	}

	@Test
	@Order(2)
	public void testStudentDetails() {
		log.info("testStudentDetails() method called");

		EntityManager manager = managerFactory.createEntityManager();

		String query = "select s.name, s.age, d.deptNo, d.deptName from StudentEntity s "
				+ "inner join DepartmentEntity d on s.deptNo = d.deptNo";

		List<Object[]> results = manager.createQuery(query, Object[].class).getResultList();

		List<Student> students = results.stream().map(object -> {
			Student student = new Student();
			student.setName((String) object[0]);
			student.setAge((int) object[1]);
			student.setDeptNo((int) object[2]);
			student.setDeptName((String) object[3]);
			return student;
		}).collect(Collectors.toList());

		students.forEach(student -> log.info(student.toString()));
	}
}
