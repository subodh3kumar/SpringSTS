package workshop.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "trades")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "trader_name")
	private String traderName;

	@Column(name = "stock_name")
	private String stockName;

	private BigDecimal price;
	private int quantity;

	@Column(name = "settlement_date")
	private LocalDate settlementDate;
	
	@Column(name = "interest_rate")
	private Double interestRate;
	
	@Column(name = "amount_payable")
	private BigDecimal totalAmountPayable;
}
