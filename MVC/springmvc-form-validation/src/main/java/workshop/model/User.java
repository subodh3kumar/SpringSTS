package workshop.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

	@NotBlank(message = "Name must not be blank")
	@Size(min = 3, max = 15, message = "Size must be between 2 and 15")
	private final String name;

	@NotNull
	@Min(value = 18, message = "Age must be greater and equal to 18")
	private final Integer age;
}
