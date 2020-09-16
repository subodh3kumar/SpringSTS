package workshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;
import workshop.exception.StatementException;
import workshop.model.Record;
import workshop.model.Records;
import workshop.model.Report;
import workshop.util.Constants;

@Slf4j
@Service
public class ValidationService {

	@Autowired
	private Environment env;

	public List<Record> getUniqueRecords(List<Record> records) {
		log.info("getUniqueRecords() method call start.");

		List<Record> duplicateRecords = new ArrayList<>();
		List<Record> uniqueRecords = new ArrayList<>();

		Set<String> uniqueRefs = new HashSet<>();

		if (CollectionUtils.isNotEmpty(records)) {
			for (Record record : records) {
				if (uniqueRefs.add(record.getReference()) == true) {
					uniqueRecords.add(record);
				} else {
					duplicateRecords.add(record);
				}
			}
		}
		return uniqueRecords;
	}

	public List<Record> readRecordsFromFile(final String path) {
		log.info("readRecordsFromFile() method call start.");

		List<Record> result = new ArrayList<>();

		if (path.endsWith(Constants.XML)) {
			List<Record> records = getRecordsFromXml(path);
			if (CollectionUtils.isNotEmpty(records)) {
				result.addAll(records);
			}
		} else if (path.endsWith(Constants.CSV)) {
			List<Record> records = getRecordsFromCsv(path);
			if (CollectionUtils.isNotEmpty(records)) {
				result.addAll(records);
			}
		}
		if (CollectionUtils.isNotEmpty(result)) {
			validateReference(result);
		}
		return result;
	}

	private void validateReference(List<Record> records) {
		log.info("validateReference() method call start.");

		Iterator<Record> iterator = records.iterator();
		while (iterator.hasNext()) {
			Record record = iterator.next();
			try {
				Long.parseLong(record.getReference());
			} catch (NumberFormatException e) {
				log.error("record is not having numeric reference.");
				log.info("removing from list: {}", record.toString());
				iterator.remove();
				continue;
			}
		}
	}

	private List<Record> getRecordsFromCsv(String path) {
		log.info("getRecordsFromCsv() method call start.");
		
		CsvToBean<Record> csvToBean = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class).withIgnoreLeadingWhiteSpace(true)
					.build();
		} catch (IOException e) {
			log.error("Exception occurred while CSV file processing.", e);
			throw new StatementException("Exception occurred while CSV file processing.");
		}
		return csvToBean.parse();
	}

	private List<Record> getRecordsFromXml(String path) {
		log.info("getRecordsFromXml() method call start.");
		
		Records records = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Records.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			records = (Records) unmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			log.error("Exception occurred while XML file processing.", e);
			throw new StatementException("Exception occurred while XML file processing.");
		}
		return records.getRecords();
	}

	public List<Report> getFailedTxns(List<Record> records) {
		log.info("getFailedTxns() method call start.");

		List<Record> failedTxnRecords = validateEndBalance(records);
		return generateReport(failedTxnRecords);
	}

	private List<Record> validateEndBalance(List<Record> records) {
		log.info("validateEndBalance() method call start.");
		List<Record> result = null;
		if (CollectionUtils.isNotEmpty(records)) {
			result = records.stream().filter(this::validateBalance).collect(Collectors.toList());
		}
		return result;
	}

	public boolean validateBalance(Record record) {
		MathContext context = new MathContext(2);
		BigDecimal spendAmount = record.getStartBalance().subtract(record.getMutation(), context);
		return spendAmount.compareTo(record.getEndBalance()) != 0;
	}

	private List<Report> generateReport(List<Record> records) {
		log.info("generateReport() method call start.");
		List<Report> result = null;
		if (CollectionUtils.isNotEmpty(records)) {
			result = records.stream().map(this::convert).collect(Collectors.toList());
		}
		return result;
	}

	private Report convert(Record record) {
		Report report = new Report();
		report.setReference(record.getReference());
		report.setDescription(record.getDescription());
		return report;
	}

	public void validateFile(MultipartFile file) {
		log.info("validateFile() method call start.");

		String fileName = file.getOriginalFilename();
		log.info("file name: {}", fileName);

		if (!(fileName.endsWith(Constants.CSV) || fileName.endsWith(Constants.XML))) {
			log.error("select CSV or XML file only.");
			throw new StatementException("select CSV or XML file only.");
		}

		if (file.isEmpty()) {
			log.error("empty file uploaded.");
			throw new StatementException("empty file uploaded.");
		}
	}

	public String writeFile(MultipartFile multipartFile) {
		log.info("writeFile() method call start.");

		String tempDir = env.getProperty(Constants.TEMP_DIR_KEY);

		File file = null;
		try {
			Path path = Paths.get(tempDir);
			Files.createDirectories(path);

			byte[] bytes = multipartFile.getBytes();
			file = new File(tempDir + multipartFile.getOriginalFilename());

			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			outputStream.write(bytes);
			outputStream.close();
		} catch (Exception e) {
			log.error("exception occurred while processing uploaded file.", e);
			throw new StatementException("some issue with file creation.");
		}
		log.info("file create successfully: {}", file.exists());
		return file.getAbsolutePath();
	}
}
