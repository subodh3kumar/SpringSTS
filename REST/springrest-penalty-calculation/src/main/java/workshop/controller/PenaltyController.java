package workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import workshop.service.PenaltyService;

@Slf4j
@RestController
public class PenaltyController {

	private PenaltyService service;

	@Autowired
	public PenaltyController(PenaltyService service) {
		this.service = service;
	}

	@GetMapping("/penalty")
	public void calculatePenalty() {
		log.info("calculatePenalty() method called");
		service.process();
	}
}
