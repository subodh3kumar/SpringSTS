package workshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

	private String url;
	private String username;
	private String password;

	@Bean
	@Profile("dev")
	public String devDatabaseConnection() {
		log.info("devDatabaseConnection() method called");
		log.info("url: " + url);
		return "Db connection for DEV environment";
	}

	@Bean
	@Profile("test")
	public String testDatabaseConnection() {
		log.info("testDatabaseConnection() method called");
		log.info("url: " + url);
		return "Db connection for TEST environment";
	}

	@Bean
	@Profile("prod")
	public String prodDatabaseConnection() {
		log.info("prodDatabaseConnection() method called");
		log.info("url: " + url);
		return "Db connection for PROD environment";
	}
}
