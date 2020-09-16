package workshop.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	@CsvBindByName(column = "Reference")
	@XmlAttribute
	private String reference;

	@CsvBindByName(column = "AccountNumber")
	@XmlElement
	private String accountNumber;

	@CsvBindByName(column = "Description")
	@XmlElement
	private String description;

	@CsvBindByName(column = "Start Balance")
	@XmlElement
	private BigDecimal startBalance;

	@CsvBindByName(column = "Mutation")
	@XmlElement
	private BigDecimal mutation;

	@CsvBindByName(column = "End Balance")
	@XmlElement
	private BigDecimal endBalance;
}
