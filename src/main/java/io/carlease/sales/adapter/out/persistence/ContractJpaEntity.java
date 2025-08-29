package io.carlease.sales.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractJpaEntity {

	@Id
	private String number;

	private String lessee;

	private String car;

	@Column(name = "price_amount")
	private long priceAmount;

	@Column(name = "price_currency")
	private String priceCurrency;

    private Integer leaseTermInMonths;

    private Double interestPerYear;

	private LocalDate approvalDate;

}
