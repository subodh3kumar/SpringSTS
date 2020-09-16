package workshop.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import workshop.exception.MyException;

@Slf4j
@Service
public class HelloService {

	@Value("${file.name}")
	private String filePath;

	public List<String> getNames() {
		log.info("getNames() method called");
		List<String> names = new ArrayList<>();
		Path path = Path.of(filePath);
		try {
			names = Files.readAllLines(path, Charset.defaultCharset());
		} catch (IOException e) {
			log.error("ERROR: ", e);
			throw new MyException("not able to process file: " + path.getFileName().toString());
		}
		log.info("list size: {}", names.size());

		if (names.size() == 0) {
			log.error("No names available");
			throw new MyException("No names avaialble");
		}
		return names;
	}

}
