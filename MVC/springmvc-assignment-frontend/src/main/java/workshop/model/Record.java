package workshop.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {

	@CsvBindByName(column = "First name")
	private String firstName;

	@CsvBindByName(column = "Sur name")
	private String surName;

	@CsvBindByName(column = "Issue count")
	private int issueCount;

	@CsvBindByName(column = "Date of birth")
	private String dob;
}
