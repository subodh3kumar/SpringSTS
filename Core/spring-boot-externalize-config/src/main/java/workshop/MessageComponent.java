package workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources(@PropertySource(value = "file:/Development/Files/Input/Misc/greetings.properties"))
public class MessageComponent {

	private static final Logger log = LoggerFactory.getLogger(MessageComponent.class);

	@Value(value = "${app.welcome}")
	private String welcomeMsg;

	@Value(value = "${app.greetings}")
	private String greetingMsg;

	@Value("${spring.application.name}")
	private String appName;

	public void display() {
		log.info("display() method ...");
		log.info("welcome message: {}", welcomeMsg);
		log.info("greeting message: {}", greetingMsg);
		log.info("app name: {}", appName);
	}
}
