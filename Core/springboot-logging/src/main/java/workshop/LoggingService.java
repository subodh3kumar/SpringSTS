package workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	private static final Logger log = LoggerFactory.getLogger(LoggingService.class);

	public int divide(int num1, int num2) {
		log.info("divide() method called - info");
		log.debug("divide() method called - debug");
		num1 = 0;
		int result = 0;
		try {
			result = num2 / num1;
		} catch (Exception e) {
			log.error("exception occurred while division", e);
		}
		log.info("processing completed");
		return result;
	}
}
