package workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import workshop.service.EmployeeService;

@Slf4j
@RestController
public class EmployeeController {

	private EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ResponseEntity<String> report() {
		log.info("report() method called");
		String filePath = service.process();
		String msg = "";
		if (filePath.isBlank()) {
			msg = "report not created";
		} else {
			msg = "report created";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
