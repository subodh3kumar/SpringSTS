package workshop.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@Column(name = "empno")
	private Integer id;

	@Column(name = "ename")
	private String name;

	@Column(name = "job")
	private String job;

	@Column(name = "mgr")
	private String mgrName;

	@Column(name = "hiredate")
	private LocalDate hireDate;

	@Column(name = "sal")
	private Double salary;

	@Column(name = "comm")
	private Double commission;

	@Column(name = "deptno")
	private Integer deptNo;
}
