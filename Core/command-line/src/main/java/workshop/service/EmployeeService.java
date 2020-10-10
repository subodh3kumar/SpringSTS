package workshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import workshop.model.Employee;
import workshop.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public void readAllEmployee() {
		List<Employee> employees = repository.findAll();
		employees.forEach(System.out::println);
	}

}
