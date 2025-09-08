package io.carlease.sales.application.service;



import io.carlease.sales.application.port.in.FilloutContractUseCase;
import io.carlease.sales.application.port.out.ContractPersistencePort;
import io.carlease.sales.domain.*;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@ApplicationLayer
@Service
public class FilloutContractService implements FilloutContractUseCase {

	Logger logger = LoggerFactory.getLogger(FilloutContractService.class);

	private final ContractPersistencePort contractPersistencePort;


	public FilloutContractService(ContractPersistencePort contractPersistencePort) {
		this.contractPersistencePort = contractPersistencePort;
	}

	public void with(ContractNumber number, Customer customer, Car car, Amount price) {
		if (!contractPersistencePort.exists(number)) {
			contractPersistencePort.save(new Contract(
					number,
					customer,
					car,
					price));
			logger.info("A contract was persisted with number " + number.number());
		}
	}

}
