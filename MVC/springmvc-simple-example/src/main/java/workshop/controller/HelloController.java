package workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {

	@GetMapping("/")
	public String sayHello(Model model) {
		log.info("sayHello() method called");
		model.addAttribute("message", "Hello World !!!");
		return "index";
	}
}
