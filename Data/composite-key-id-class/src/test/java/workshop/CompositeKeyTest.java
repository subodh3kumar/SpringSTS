package workshop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import workshop.entity.StudentEntity;
import workshop.entity.StudentId;
import workshop.repository.StudentRepository;

import java.util.Optional;

@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CompositeKeyTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    public void findByStudentId() {
        StudentId studentId = new StudentId(1000, "SUBODH");
        Optional<StudentEntity> studentById = studentRepository.findById(studentId);
        if (studentById.isPresent()) {
            StudentEntity studentEntity = studentById.get();
            log.info("student: {}", studentEntity);
        }
    }
}
