package workshop.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Employee {

	private int id;
	private String name;
	private String job;
	private String mgrName;
	private LocalDate hireDate;
	private double salary;
	private double commission;
	private int deptNo;
	
}
