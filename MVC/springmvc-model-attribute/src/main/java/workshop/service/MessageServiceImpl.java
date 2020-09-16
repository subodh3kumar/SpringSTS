package workshop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Value("${message.greeting}")
	private String message;

	@Override
	public String getMessage() {
		return message;
	}
}
