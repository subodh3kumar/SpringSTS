package workshop.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.EmployeeEntity;
import workshop.model.Employee;
import workshop.repository.EmployeeRepository;

@Slf4j
@Service
public class EmployeeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeRepository repository;

	@Value("${local.directory}")
	private String localDirectory;

	public String process() {
		log.info("process() method called");

		String[] columns = getTableColumnNames().stream().toArray(String[]::new);
		log.info(Arrays.toString(columns));

		List<EmployeeEntity> entities = repository.findAll();
		List<Employee> employees = entities.stream().map(this::convert).collect(Collectors.toList());

		Path path = createReport(columns, employees);
		return path.getFileName().toString();
	}

	private Path createReport(String[] columns, List<Employee> employees) {
		log.info("createReport() method called");

		Path path = Paths.get(localDirectory + "employee.xlsx");

		try (Workbook workbook = new XSSFWorkbook(); FileOutputStream out = new FileOutputStream(path.toFile())) {
			Sheet sheet = workbook.createSheet();

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Header Row
			Row headerRow = sheet.createRow(0);

			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Rows
			int rowIndex = 1;
			for (Employee employee : employees) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(employee.getId());
				row.createCell(1).setCellValue(employee.getName());
				row.createCell(2).setCellValue(employee.getJob());
				row.createCell(3).setCellValue(employee.getMgrName());
				row.createCell(4).setCellValue(employee.getHireDate());
				row.createCell(5).setCellValue(employee.getSalary());
				row.createCell(6).setCellValue(employee.getCommission());
				row.createCell(7).setCellValue(employee.getDeptNo());
			}
			workbook.write(out);
		} catch (IOException e) {
			log.error("exception occurred while creating file", e);
		}
		return path;
	}

	private double checkNull(Double dbl) {
		if (dbl == null) {
			return 0;
		}
		return dbl;
	}

	public Employee convert(EmployeeEntity entity) {
		Employee bean = new Employee();
		bean.setId(entity.getId());
		bean.setName(entity.getName());
		bean.setJob(entity.getJob());
		bean.setMgrName(entity.getMgrName());
		bean.setHireDate(entity.getHireDate());
		bean.setSalary(entity.getSalary());
		bean.setCommission(checkNull(entity.getCommission()));
		bean.setDeptNo(entity.getDeptNo());
		return bean;
	}

	private List<String> getTableColumnNames() {
		String query = "SELECT * FROM EMPLOYEE";
		List<String> columnNames = this.jdbcTemplate.query(query, new EmployeeResultSetExtractor());
		log.info("total column available: " + columnNames.size());
		return columnNames;
	}
}
