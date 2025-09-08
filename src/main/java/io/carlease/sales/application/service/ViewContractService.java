package io.carlease.sales.application.service;

import io.carlease.sales.application.port.in.ViewContractUseCase;
import io.carlease.sales.application.port.out.ContractPersistencePort;
import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ApplicationLayer
@Service
public class ViewContractService implements ViewContractUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ViewContractService.class);

	private final ContractPersistencePort contractPersistencePort;

	public ViewContractService(ContractPersistencePort contractPersistencePort) {
		this.contractPersistencePort = contractPersistencePort;
	}
	
	public Contract with(ContractNumber number) {
		var contract = this.contractPersistencePort.with(number);
		logger.debug("Repository returned contract: " + contract);
		return contract;
	}

}
