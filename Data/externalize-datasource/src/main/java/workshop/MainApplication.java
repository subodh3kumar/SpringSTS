package workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workshop.repository.NameRepository;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private final NameRepository nameRepository;

    public MainApplication(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @Override
    public void run(String... args) {
        nameRepository.load();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
