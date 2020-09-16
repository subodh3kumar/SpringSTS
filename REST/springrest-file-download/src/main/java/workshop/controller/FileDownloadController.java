package workshop.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Payload;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileDownloadController {

	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/v1/download")
	public ResponseEntity<Resource> fileDownloadV1() throws Exception {
		log.info("fileDownloadV1() method called");

		Resource resource = new ClassPathResource("static/v1.txt");
		InputStream inputStream = resource.getInputStream();
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=v1.txt");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(inputStreamResource);
	}

	@GetMapping("/v2/download")
	public ResponseEntity<Resource> fileDownloadV2() throws Exception {
		log.info("fileDownloadV2() method called");

		Resource resource = resourceLoader.getResource("classpath:static/v2.txt");
		Path path = Paths.get(resource.getFile().getAbsolutePath());
		ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(path));

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=v2.txt");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(byteArrayResource);
	}

	@GetMapping("/v3/download")
	public ResponseEntity<Resource> fileDownloadV3(HttpServletRequest request) throws Exception {
		log.info("fileDownloadV3() method called");

		Path path = Paths.get("C:/Development/Files/Input/algs4-data/leipzig1M.txt");
		Resource resource = new UrlResource(path.toUri());

		String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		if (contentType == null) {
			contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}
		log.info("content type: " + contentType);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
				.contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

	@PostMapping(value = "/v4/download", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> excelDownload(@RequestBody Payload payload) throws Exception {
		log.info("excelDownload() method called");
		log.info("payload: " + payload);
		Path path = Paths.get("C:/Development/Files/Input/w3schools/Customers.xlsx");
		Resource resource = new UrlResource(path.toUri());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

	}

	@PostMapping(value = "/v5/download", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> largeFileDownload(@RequestBody Payload payload) throws Exception {
		log.info("excelDownload() method called");

		log.info("payload: " + payload);
		String absolutePath = payload.getFileDirectory() + payload.getFileName();

		Path path = Paths.get(absolutePath);
		Resource resource = new UrlResource(path.toUri());

		String contentDisposition = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);

	}
}
