package workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

	private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	private LoggingService service;

	@GetMapping("/")
	public String msg() {
		log.info("msg() method called - info");
		log.debug("msg() method called - debug");

		int result = service.divide(5, 10);
		log.info("result is: {}", result);

		return "check out the log messages";
	}
}
