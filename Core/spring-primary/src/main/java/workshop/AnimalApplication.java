package workshop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import workshop.config.AnimalConfig;
import workshop.service.AnimalService;

public class AnimalApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnimalConfig.class);
		AnimalService animalService = context.getBean(AnimalService.class);
		animalService.print();
		context.close();
	}
}
