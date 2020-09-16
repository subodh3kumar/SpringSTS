package workshop.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Trade;
import workshop.exception.PenaltyException;
import workshop.repository.PenaltyRepository;

@Slf4j
@Service
public class PenaltyService {

	private static final String FILE_PATH = "src/main/resources/static/stock_details.txt";

	private PenaltyRepository repository;

	@Autowired
	public PenaltyService(PenaltyRepository repository) {
		this.repository = repository;
	}

	public void process() {
		List<String> lines = loadRecordFromFile();
		List<Trade> trades = lines.stream().map(this::convert).collect(Collectors.toList());
		log.info("trades size: {}", trades.size());
		saveTrdes(trades);
	}

	private void saveTrdes(List<Trade> trades) {
		log.info("saveTrdes() method called");
		repository.saveAll(trades);
		repository.flush();
		log.info("trades saved successfully");
	}

	private Trade convert(String line) {
		Trade trade = new Trade();
		String[] attributes = line.split("\\|", -1);
		if (attributes.length == 6) {
			trade.setTraderName(attributes[1]);
			trade.setStockName(attributes[2]);
			trade.setPrice(new BigDecimal(attributes[3]));
			trade.setQuantity(Integer.parseInt(attributes[4]));
			trade.setSettlementDate(LocalDate.parse(attributes[5]));
		}
		return trade;
	}

	private List<String> loadRecordFromFile() {
		log.info("loadRecordFromFile() method called");
		Path path = Paths.get(FILE_PATH);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path);
			lines.remove(0);
		} catch (IOException e) {
			log.error("trade file not loading", e);
			throw new PenaltyException("trade file not loading");
		}
		return lines;
	}
}
