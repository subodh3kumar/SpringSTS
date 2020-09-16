package workshop.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import workshop.service.UserService;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void saveUsers(@RequestParam("files") MultipartFile[] files) {
		log.info("saveUsers() method called");

		for (MultipartFile file : files) {
			service.save(file);
		}
	}

	@GetMapping
	public CompletableFuture<ResponseEntity<?>> getAllUsers() {
		log.info("getAllUsers() method called");
		return service.getAllUsers().thenApply(ResponseEntity::ok);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping("/concurrent")
	public void findAllUsers() {
		log.info("findAllUsers() method called");
		service.getAllUsers();
		service.getAllUsers();
		service.getAllUsers();
	}
}
