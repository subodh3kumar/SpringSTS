package workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SimpleExampleApplication {

	public static void main(String[] args) {
		log.info("main() method called");
		SpringApplication.run(SimpleExampleApplication.class, args);
	}
}
