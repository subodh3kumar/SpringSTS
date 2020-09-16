package workshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import workshop.service.HelloService;

@Slf4j
@RestController
public class HelloController {

	private HelloService service;

	public HelloController(HelloService service) {
		this.service = service;
	}

	@GetMapping("/hello")
	public String sayHello() {
		log.info("sayHello() method called");
		List<String> names = service.getNames();
		return names.toString();
	}
}
