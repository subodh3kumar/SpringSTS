package workshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import workshop.config.DatabaseConfig;

@RestController
public class ProfileController {

	private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

	@Value("${app.message}")
	private String message;

	@GetMapping("/")
	public String message() {
		log.info("message: " + message);
		return message;
	}
}
