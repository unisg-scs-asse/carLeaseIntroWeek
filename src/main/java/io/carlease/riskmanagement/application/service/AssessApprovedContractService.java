package io.carlease.riskmanagement.application.service;

import io.carlease.riskmanagement.application.port.in.AssessApprovedContractPort;
import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.Contract;
import io.carlease.riskmanagement.domain.ContractNumber;
import io.carlease.riskmanagement.domain.SignDate;
import org.springframework.stereotype.Component;

import static java.time.temporal.ChronoField.*;

@Component
class AssessApprovedContractService implements AssessApprovedContractPort {

	private final ContractRepositoryPort contracts;

	public AssessApprovedContractService(ContractRepositoryPort contracts) {
		this.contracts = contracts;
	}

	@Override
	public void with(String number, int year, int month, int dayOfMonth) {
		YEAR.checkValidValue(year);
		MONTH_OF_YEAR.checkValidValue(month);
		DAY_OF_MONTH.checkValidValue(dayOfMonth);

		contracts.save(new Contract(ContractNumber.of(number), SignDate.of(year, month, dayOfMonth)));
	}
}
