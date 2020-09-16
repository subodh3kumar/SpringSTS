package workshop.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Payload;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileDownloadControllerTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	@BeforeEach
	public void init() {
		restTemplate = new RestTemplate();
	}

	@AfterEach
	public void close() {
		restTemplate = null;
	}

	@Test
	public void downloadFileV1() throws Exception {
		String url = "http://localhost:" + port + "/file/v1/download";
		String absPath = "C:/Development/Files/Output/v1.txt";

		Files.deleteIfExists(Paths.get(absPath));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<byte[]> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);

		MediaType contentType = response.getHeaders().getContentType();
		log.info("content type: " + contentType);

		ContentDisposition contentDisposition = response.getHeaders().getContentDisposition();
		log.info("file name: " + contentDisposition.getFilename());

		Files.write(Paths.get(absPath), response.getBody());

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		String msg = Files.readString(Paths.get(absPath));
		assertThat(msg).isEqualTo("Spring framework");
	}

	@Test
	public void downloadFileV2() throws Exception {
		String url = "http://localhost:" + port + "/file/v2/download";
		String absPath = "C:/Development/Files/Output/v2.txt";

		Files.deleteIfExists(Paths.get(absPath));

		restTemplate.execute(url, HttpMethod.GET, null, response -> {
			StreamUtils.copy(response.getBody(), new FileOutputStream(new File(absPath)));
			return null;
		});

		// assertNotNull(file);
		String msg = Files.readString(Paths.get(absPath));
		assertThat(msg).isEqualTo("Spring Boot");
	}

	@Test
	public void downloadFileV3() throws Exception {
		String url = "http://localhost:" + port + "file/excel/download";
		String fileDirectory = "C:/Development/Files/Output/";

		Payload payload = new Payload();
		payload.setFileDirectory("report-path");
		payload.setFileName("view-name");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

		HttpEntity<Payload> entity = new HttpEntity<Payload>(payload, headers);

		ResponseEntity<byte[]> response = this.restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);

		String fileName = response.getHeaders().getContentDisposition().getFilename();
		log.info("file name: {}", fileName);

		Files.createDirectories(Paths.get(fileDirectory));
		String absolutePath = fileDirectory + fileName;

		Files.write(Paths.get(absolutePath), response.getBody());

		assertThat(Files.exists(Paths.get(absolutePath))).isEqualTo(true);
		assertThat(Paths.get(absolutePath).getFileName().toString()).isEqualTo(fileName);
	}

	@Test
	public void downloadFileV4() {
		String url = "http://localhost:8081/file/v5/download";

		Payload payload = new Payload();
		payload.setFileDirectory("C:/Development/Files/Input/algs4-data/");
		payload.setFileName("largeEWD.txt");

		RequestCallback requestCallback = this::doWithRequest;
		requestCallback = restTemplate.httpEntityCallback(payload);

		Path path = restTemplate.execute(url, HttpMethod.POST, requestCallback, this::extractData);

		String fileName = path.getFileName().toString();
		log.info("File name: " + fileName);
	}

	private void doWithRequest(ClientHttpRequest request) {
		request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	}

	private Path extractData(ClientHttpResponse response) throws IOException {
		String fileName = response.getHeaders().getContentDisposition().getFilename();
		log.info("file name: " + fileName);
		Path path = Paths.get("C:/Development/Files/Output/" + fileName);
		Files.deleteIfExists(path);
		Files.copy(response.getBody(), path);
		return path;
	}
}
