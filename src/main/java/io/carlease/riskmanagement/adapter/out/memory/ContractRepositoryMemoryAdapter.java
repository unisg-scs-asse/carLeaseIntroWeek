package io.carlease.riskmanagement.adapter.out.memory;

import io.carlease.riskmanagement.application.port.out.ContractRepositoryPort;
import io.carlease.riskmanagement.domain.Contract;
import io.carlease.riskmanagement.domain.ContractNumber;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContractRepositoryMemoryAdapter implements ContractRepositoryPort {

	private final Map<ContractNumber, Contract> repo;
	
	public ContractRepositoryMemoryAdapter() {
		repo = new HashMap<>();
	}

	@Override
	public Contract findById(ContractNumber number) {
		return repo.get(number);
	}

	@Override
	public Collection<Contract> findAll() {
		return repo.values();
	}

	@Override
	public void save(Contract contract) {
		repo.put(contract.identity(), contract);
	}

}
