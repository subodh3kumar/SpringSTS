package workshop.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@Column(name = "empno")
	private Integer id;

	@Column(name = "ename")
	private String name;

	private String job;

	@Column(name = "mgr")
	private String mgrName;

	private LocalDate hiredate;

	@Column(name = "sal")
	private Double salary;

	@Column(name = "comm")
	private Double commission;

	@Column(name = "deptno")
	private Integer deptNo;
}
