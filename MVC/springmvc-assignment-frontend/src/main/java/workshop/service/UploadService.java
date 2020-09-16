package workshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Record;
import workshop.util.Constants;

@Slf4j
@Service
public class UploadService {

	public List<Record> getRecordsFromCsvFile(MultipartFile multipartFile) {
		log.info("getRecordsFromCsvFile() method call start.");

		String path = writeFile(multipartFile);
		log.info("file path: {}", path);

		var records = readFile(path);
		if (CollectionUtils.isNotEmpty(records)) {
			records = validateDob(records);
		}
		return records;
	}

	private List<Record> validateDob(List<Record> records) {
		log.info("validateDob() method call start.");
		return records.stream()
				.filter(record -> record.getDob().contains("T"))
				.map(this::modifyDob)
				.collect(Collectors.toList());
	}

	private Record modifyDob(Record record) {
		var dob = record.getDob();
		String[] array = dob.split("T", -1);
		record.setDob(array[0]);
		return record;
	}

	private List<Record> readFile(String path) {
		log.info("readFile() method call start.");

		CsvToBean<Record> csvToBean = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			csvToBean = new CsvToBeanBuilder<Record>(reader)
					.withType(Record.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			log.info("deleted the created csv file: {}", Files.deleteIfExists(Paths.get(path)));
		} catch (IOException e) {
			log.error("exception occurred while CSV file processing.", e);
		}
		return csvToBean.parse();
	}

	private String writeFile(MultipartFile multipartFile) {
		File file = null;
		if (!multipartFile.isEmpty()) {
			try {
				byte[] bytes = multipartFile.getBytes();
				file = new File(Constants.PATH + multipartFile.getOriginalFilename());

				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
				outputStream.write(bytes);
				outputStream.close();
			} catch (Exception e) {
				log.error("exception occurred while processing uploaded file.", e);
			}
		}
		log.info("file create successfully: {}", file.exists());
		return file.getAbsolutePath();
	}
}
