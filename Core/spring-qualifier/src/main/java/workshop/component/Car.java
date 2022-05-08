package workshop.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("car")
@Component
public class Car implements Vehicle {

	@Override
	public void start() {
		System.out.println("car started.");
	}

	@Override
	public void stop() {
		System.out.println("car stopped.");
	}
}
