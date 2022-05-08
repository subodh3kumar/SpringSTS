package workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(StudentId.class)
@Table(name = "STUDENT")
public class StudentEntity {

    @Id
    @Column(name = "ROLL_NUMBER")
    private Integer rollNumber;

    @Id
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private Integer age;
}
