package workshop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Employee {

	@EmbeddedId
	private EmployeeIdentity id;

	@Column
	private String name;

	@Column
	private String email;

	public Employee() {
	}

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public EmployeeIdentity getId() {
		return id;
	}

	public void setId(EmployeeIdentity id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
