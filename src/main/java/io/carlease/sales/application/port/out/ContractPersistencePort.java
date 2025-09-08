package io.carlease.sales.application.port.out;

import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface ContractPersistencePort {

	Contract with(ContractNumber number);

	void save(Contract contract);

	boolean exists(ContractNumber number);

}
