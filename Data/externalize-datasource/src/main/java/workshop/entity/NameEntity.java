package workshop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "NAMES")
public class NameEntity {

    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private int age;
}
