package workshop.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private Long id;
	private String name;
	private Gender sex;
	private Map<String, Address> addresses = new HashMap<>();

	public Customer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Map<String, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, Address> addresses) {
		this.addresses = addresses;
	}
}
