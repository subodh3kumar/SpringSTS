package workshop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import workshop.controller.FileDownloadController;

@SpringBootTest
class FileDownloadApplicationTest {

	@Autowired
	private FileDownloadController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
