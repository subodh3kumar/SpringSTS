package workshop.resource;

import org.springframework.stereotype.Component;

import workshop.service.EmployeeService;

@Component
public class EmployeeResource {

	private EmployeeService service;

	public EmployeeResource(EmployeeService service) {
		this.service = service;
	}

	public void processEmployee() {
		service.readAllEmployee();
	}
}
