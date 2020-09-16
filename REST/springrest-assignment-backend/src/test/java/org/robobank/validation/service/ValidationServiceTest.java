package org.robobank.validation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;

import workshop.AssignmentBackendApplication;
import workshop.exception.StatementException;
import workshop.model.Record;
import workshop.model.Report;
import workshop.service.ValidationService;

@SpringBootTest(classes = AssignmentBackendApplication.class)
class ValidationServiceTest {

	@InjectMocks
	ValidationService service;

	@Mock
	Environment env;

	@Test
	@DisplayName("unique records test")
	void testUniqueRecords() {
		List<Record> records = getRecords();
		records = service.getUniqueRecords(getRecords());

		assertThat(records).isNotEmpty();
		assertEquals(uniqueRecords(), records);
	}

	@Test
	@DisplayName("failed transaction test")
	void testFailedTxns() {
		List<Record> records = uniqueRecords();
		List<Report> report = service.getFailedTxns(records);
		
		assertThat(report).isNotEmpty();
		assertEquals(failedTxnRecords(), report);
	}

	@Test()
	@DisplayName("read from file test")
	void testReadRecordsFromFile() {
		String path = "C:\\Development\\Files\\Robobank\\Output\\records.csv";
		List<Record> records = service.readRecordsFromFile(path);

		assertThat(records).isNotEmpty();
	}

	@Test
	@DisplayName("multipart invalid file testing")
	void testInvalidFile() throws Exception {
		File file = new File("C:/Development/Files/Robobank/Statements/records.txt");
		InputStream is = new FileInputStream(file);

		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.txt", "multipart/form-data", is);

		StatementException exception = assertThrows(StatementException.class, () -> {
			service.validateFile(mockMultipartFile);
		});
		assertEquals("select CSV or XML file only.", exception.getMessage());
	}

	@Test
	@DisplayName("multipart empty file testing")
	void testEmptyFile() throws Exception {
		File file = new File("C:/Development/Files/Robobank/Statements/empty.xml");
		InputStream is = new FileInputStream(file);

		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "empty.xml", "multipart/form-data", is);

		StatementException exception = assertThrows(StatementException.class, () -> {
			service.validateFile(mockMultipartFile);
		});
		assertEquals("empty file uploaded.", exception.getMessage());
	}

	@Test
	@DisplayName("write file test")
	void testWriteFile() throws Exception {
		File file = new File("C:/Development/Files/Robobank/Statements/records.csv");
		InputStream is = new FileInputStream(file);

		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", is);

		when(env.getProperty("default.temp.directory")).thenReturn("C:/Development/Files/Robobank/Output/");

		String path = service.writeFile(mockMultipartFile);

		assertThat(path).isNotBlank();
		assertEquals("C:\\Development\\Files\\Robobank\\Output\\records.csv", path);
	}

	public static List<Record> getRecords() {
		return Arrays.asList(
				new Record("194261", "NL91RABO0315273637", "Clothes from Jan Bakker", BigDecimal.valueOf(200),
						BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
				new Record("112806", "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(300),
						BigDecimal.valueOf(100), BigDecimal.valueOf(30)),
				new Record("112806", "NL69ABNA0433647324", "Clothes for Richard de Vries", BigDecimal.valueOf(500),
						BigDecimal.valueOf(250), BigDecimal.valueOf(100)),
				new Record("139524", "NL43AEGO0773393871", "Flowers from Jan Bakke", BigDecimal.valueOf(600),
						BigDecimal.valueOf(450), BigDecimal.valueOf(150)),
				new Record("141223", "NL93ABNA0585619023", "Clothes from Erik Bakker", BigDecimal.valueOf(1500),
						BigDecimal.valueOf(450), BigDecimal.valueOf(1000)));
	}

	public static List<Record> uniqueRecords() {
		return Arrays.asList(
				new Record("194261", "NL91RABO0315273637", "Clothes from Jan Bakker", BigDecimal.valueOf(200),
						BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
				new Record("112806", "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(300),
						BigDecimal.valueOf(100), BigDecimal.valueOf(30)),
				new Record("139524", "NL43AEGO0773393871", "Flowers from Jan Bakke", BigDecimal.valueOf(600),
						BigDecimal.valueOf(450), BigDecimal.valueOf(150)),
				new Record("141223", "NL93ABNA0585619023", "Clothes from Erik Bakker", BigDecimal.valueOf(1500),
						BigDecimal.valueOf(450), BigDecimal.valueOf(1000)));
	}

	public static List<Report> failedTxnRecords() {
		return Arrays.asList(new Report("112806", "Clothes for Willem Dekker"),
				new Report("141223", "Clothes from Erik Bakker"));
	}
}
