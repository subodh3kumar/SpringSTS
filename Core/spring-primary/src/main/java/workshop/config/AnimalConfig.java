package workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import workshop.model.Animal;

@Configuration
@ComponentScan("workshop")
public class AnimalConfig {

	@Bean
	public Animal tigerBean() {
		return new Animal("Tiger");
	}
	
	@Bean
	@Primary
	public Animal KangarooBean() {
		return new Animal("Kangaroo");
	}
	
	@Bean
	public Animal lionBean() {
		return new Animal("Lion");
	}
}
