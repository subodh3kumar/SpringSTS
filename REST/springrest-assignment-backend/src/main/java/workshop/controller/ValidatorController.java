package workshop.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Record;
import workshop.model.Report;
import workshop.model.ResponseMessage;
import workshop.service.ValidationService;

@Slf4j
@RestController
@RequestMapping("/validate")
public class ValidatorController {

	@Autowired
	private ValidationService service;

	@PostMapping(value = "/statement", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> validateStatement(@RequestParam("file") MultipartFile file) {
		log.info("validateStatement() method call start.");

		service.validateFile(file);
		String path = service.writeFile(file);
		log.info("Absolute path created: {}", path);

		List<Record> records = service.readRecordsFromFile(path);
		records = service.getUniqueRecords(records);
		List<Report> failedTxns = service.getFailedTxns(records);

		ResponseMessage message = new ResponseMessage();
		if (CollectionUtils.isNotEmpty(failedTxns)) {
			message.setMessage("There are " + failedTxns.size() + " failed transaction available.");
			message.setReports(failedTxns);
		} else {
			message.setMessage("There are no failed transaction available.");
		}
		return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
	}
}
