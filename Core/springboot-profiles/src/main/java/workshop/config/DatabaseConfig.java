package workshop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

	private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

	private String url;
	private String username;
	private String password;

	@Bean
	@Profile("dev")
	public String devDatabaseConnection() {
		log.info("devDatabaseConnection() method called");
		log.info("url: " + url);
		return "DB connection for DEV environment";
	}

	@Bean
	@Profile("uat")
	public String uatDatabaseConnection() {
		log.info("testDatabaseConnection() method called");
		log.info("url: " + url);
		return "DB connection for UAT environment";
	}

	@Bean
	@Profile("prod")
	public String prodDatabaseConnection() {
		log.info("prodDatabaseConnection() method called");
		log.info("url: " + url);
		return "DB connection for PROD environment";
	}
}
