package workshop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import workshop.models.Employee;
import workshop.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	private EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public void readAllEmployee() {
		List<Employee> employees = repository.findAll();
		employees.forEach(emp -> log.info(emp.toString()));
	}
}
