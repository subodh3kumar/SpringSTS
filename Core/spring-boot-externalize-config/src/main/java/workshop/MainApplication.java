package workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

	private final MessageComponent messageComponent;

	public MainApplication(MessageComponent messageComponent) {
		this.messageComponent = messageComponent;
	}

	@Override
	public void run(String... args) {
		log.info("start() method...");
		messageComponent.display();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
