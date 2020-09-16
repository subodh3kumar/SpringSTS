package workshop.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseMessage {

	private String message;
	private List<Report> reports;
}
