package workshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Record;
import workshop.service.UploadService;
import workshop.util.Constants;

@Slf4j
@Controller
public class UploadController {

	private List<Record> records;

	@Autowired
	private UploadService service;

	@GetMapping("/")
	public String page(Model model) {
		log.info("page() method call start.");
		return "upload";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {
		log.info("upload() method call start.");

		String fileName = file.getOriginalFilename();
		log.info("file name {}: " + fileName);

		if (Constants.EMPTY.equals(fileName)) {
			log.info("No file uploaded.");
			redirectAttributes.addFlashAttribute("fileError", "Please select file.");
			return "redirect:/";
		}
		if (!fileName.endsWith(Constants.CSV)) {
			log.info("No CSV file uploaded.");
			redirectAttributes.addFlashAttribute("fileError", "Please select only csv file.");
			return "redirect:/";
		}
		if (file.isEmpty()) {
			log.info("empty CSV file uploaded.");
			redirectAttributes.addFlashAttribute("fileError", "Empty file uploaded.");
			return "redirect:/";
		}

		records = service.getRecordsFromCsvFile(file);
		log.info("records list size: {}", records.size());
		if (CollectionUtils.isNotEmpty(records)) {
			model.addAttribute("records", records);
		} else {
			model.addAttribute("empty", "No records available.");
		}
		return "upload";
	}

	@PostMapping("/filter")
	public String filter(@RequestParam("filterValue") String filterValue, Model model) {
		log.info("filter() method call start.");
		log.info("filter value: {}", filterValue);

		List<Record> filterRecords = records.stream()
				.filter(record -> record.getIssueCount() == Integer.parseInt(filterValue))
				.collect(Collectors.toList());
		log.info("filterRecords list size: {}", filterRecords.size());
		if (CollectionUtils.isNotEmpty(filterRecords)) {
			model.addAttribute("records", filterRecords);
		} else {
			model.addAttribute("empty", "No issue count with value " + filterValue + " available.");
		}
		return "upload";
	}
}
