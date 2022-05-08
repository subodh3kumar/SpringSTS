package workshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Slf4j
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		log.info("main() method start");
		SpringApplication.run(MainApplication.class, args);
	}
}
