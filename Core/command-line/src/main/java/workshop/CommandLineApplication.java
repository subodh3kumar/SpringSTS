package workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import workshop.resource.EmployeeResource;

@SpringBootApplication
public class CommandLineApplication implements CommandLineRunner {

	private final EmployeeResource resource;

	public CommandLineApplication(EmployeeResource resource) {
		this.resource = resource;
	}

	@Override
	public void run(String... args) throws Exception {
		resource.processEmployee();
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandLineApplication.class, args);
	}
}
