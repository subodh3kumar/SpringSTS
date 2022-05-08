package workshop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import workshop.service.VehicleService;

public class MainApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.scan("workshop");
		context.refresh();

		VehicleService service = context.getBean(VehicleService.class);
		service.process();

		context.close();
	}
}
