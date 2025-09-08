package io.carlease.sales.application.service;


import io.carlease.sales.adapter.out.serialization.ContractSerializationAdapter;
import io.carlease.sales.application.port.in.FilloutContractUseCase;
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

	private final ContractSerializationAdapter contractSerializationPersistence;
	public FilloutContractService() {
		this.contractSerializationPersistence = new ContractSerializationAdapter();
	}

	public void with(ContractNumber number, Customer customer, Car car, Amount price) {
		if (!contractSerializationPersistence.exists(number)) {
			contractSerializationPersistence.save(new Contract(
					number,
					customer,
					car,
					price));
			logger.info("A contract was persisted with number " + number.number());
		}

	}

}
