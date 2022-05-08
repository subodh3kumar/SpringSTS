package workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workshop.model.Animal;

@Service
public class AnimalService {

	@Autowired
	private Animal animal;
	
	public void print() {
		System.out.println(animal);
	}
}
