package io.carlease.riskmanagement.application.service;

import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.Contract;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@ApplicationLayer
@Service
public class ListContractsService {

	private final ContractRepositoryPort contracts;

	public ListContractsService(ContractRepositoryPort contracts) {
		this.contracts = contracts;
	}

	public Collection<Contract> all() {
		return contracts.findAll();
	}

}
