package io.carlease.riskmanagement.application.service;

import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.Contract;
import io.carlease.riskmanagement.domain.ContractNumber;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@ApplicationLayer
@Service
public class ReadContractService {

	private final ContractRepositoryPort contracts;

	public ReadContractService(ContractRepositoryPort contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public Contract readContract(ContractNumber number) {
		requireNonNull(number);

		return this.contracts.findById(number);
	}

}
