package workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import workshop.component.Vehicle;

@Service
public class VehicleService {

	@Autowired
	@Qualifier("car")
	private Vehicle vehicle;

	public void process() {
		vehicle.start();
		vehicle.stop();
	}
}
