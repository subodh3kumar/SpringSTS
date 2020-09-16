package workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoggingController {

	@Autowired
    private LoggingService service;

    @GetMapping("/")
    public String msg() {
        log.info("msg() method called - info");
        log.debug("msg() method called - debug");

        int result = service.divide(5,10);
        log.info("result is: {}", result);

        return "check out the log messages";
    }
}
