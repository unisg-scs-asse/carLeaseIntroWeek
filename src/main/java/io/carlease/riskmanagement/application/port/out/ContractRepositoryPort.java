package io.carlease.riskmanagement.application.port.out;

import io.carlease.riskmanagement.domain.Contract;
import io.carlease.riskmanagement.domain.ContractNumber;
import org.jmolecules.ddd.annotation.Repository;

import java.util.Collection;

@Repository
public interface ContractRepositoryPort {

	Contract findById(ContractNumber number);

	Collection<Contract> findAll();

	void save(Contract contract);

}
