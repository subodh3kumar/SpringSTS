package workshop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Employee {

	@EmbeddedId
	private EmployeeIdentity id;

	@Column
	private String name;

	public Employee() {
	}

	public Employee(String name) {
		this.name = name;
	}
}
