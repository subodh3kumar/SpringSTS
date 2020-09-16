package workshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProfileController {

	@Value("${app.message}")
	private String message;

	@GetMapping("/")
	public String message() {
		log.info("message: " + message);
		return message;
	}
}
