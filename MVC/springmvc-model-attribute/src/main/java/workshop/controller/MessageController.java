package workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import workshop.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService service;

	@GetMapping("/one")
	public String pageOne() {
		return "one";
	}

	@GetMapping("/two")
	public String pageTwo() {
		return "two";
	}

	@ModelAttribute("msg")
	public String message() {
		return service.getMessage();
	}
}
