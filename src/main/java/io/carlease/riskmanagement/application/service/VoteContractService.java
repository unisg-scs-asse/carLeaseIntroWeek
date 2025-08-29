package io.carlease.riskmanagement.application.service;

import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.ContractNumber;
import io.carlease.riskmanagement.domain.VoteResult;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@ApplicationLayer
@Service
public class VoteContractService {

	private final ContractRepositoryPort contracts;

	public VoteContractService(ContractRepositoryPort contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public void vote(ContractNumber number, VoteResult result) {
		requireNonNull(number);
		requireNonNull(result);

		var contract = this.contracts.findById(number);

		contract.vote(result);

		this.contracts.save(contract);
	}

}
