package workshop.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import workshop.model.User;

@Slf4j
@Controller
public class FormValidationController {

	@GetMapping("/")
	public String showForm(User user) {
		log.info("showForm() method called");
		return "form";
	}

	@PostMapping("/")
	public String verifyForm(@Valid User user, Errors errors) {
		log.info("verifyForm() method called");
		
		if (errors.hasErrors()) {
			return "form";
		}
		log.info("successful validation: " + user.toString());
		return "redirect:/welcome";
	}
}
