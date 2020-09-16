package org.robobank.upload.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import workshop.AssignmentFrontendApplication;
import workshop.model.Record;
import workshop.service.UploadService;

@SpringBootTest(classes = AssignmentFrontendApplication.class)
class UploadServiceTest {

	@InjectMocks
	UploadService service;

	@Test
	@DisplayName("csv file records test")
	void testGetRecordsFromCsvFile() throws Exception {
		File file = new File("C:\\Development\\Files\\Robobank\\Upload\\issues.csv");
		InputStream is = new FileInputStream(file);
		
		MockMultipartFile multipartFile = new MockMultipartFile("file", "issues.csv", MediaType.MULTIPART_FORM_DATA_VALUE, is);
		
		List<Record> records = service.getRecordsFromCsvFile(multipartFile);
		
		assertThat(records).isNotEmpty();
		assertEquals(original(), records);
	}

	public List<Record> original() {
		return Arrays.asList(new Record("Theo", "Jansen", 5, "1978-01-02"),
				new Record("Thor", "Rangrook", 5, "1988-01-02"), 
				new Record("Tony", "Stark", 5, "1979-01-02"), 
				new Record("Captain", "America", 5, "1968-01-02"),
				new Record("Fiona", "de Vries", 7, "1950-11-12"), 
				new Record("Petra", "Boersma", 1, "2001-04-20"));
	}
}
