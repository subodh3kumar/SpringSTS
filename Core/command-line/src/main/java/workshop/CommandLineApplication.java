package workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import workshop.resource.EmployeeResource;

@SpringBootApplication
public class CommandLineApplication implements CommandLineRunner {

	private EmployeeResource resource;

	@Autowired
	public CommandLineApplication(EmployeeResource resource) {
		this.resource = resource;
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandLineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		resource.processEmployee();
	}
}
