package workshop.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("bike")
@Component
public class Bike implements Vehicle {

	@Override
	public void start() {
		System.out.println("bike started");
	}

	@Override
	public void stop() {
		System.out.println("bike stopped");
	}
}
