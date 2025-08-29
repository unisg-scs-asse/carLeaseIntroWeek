package io.carlease.riskmanagement.application.service;

import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.ContractNumber;
import io.carlease.riskmanagement.domain.CreditRating;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@ApplicationLayer
@Service
public class CheckCreditRatingService {

	private final ContractRepositoryPort contracts;

	public CheckCreditRatingService(ContractRepositoryPort contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public void checkCreditRating(ContractNumber number, CreditRating rating) {
		requireNonNull(number);
		requireNonNull(rating);

		var contract = this.contracts.findById(number);

		contract.checkCreditRating(rating);

		this.contracts.save(contract);
	}

}
