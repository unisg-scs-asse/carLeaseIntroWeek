package io.carlease.sales.adapter.out.persistence;

import io.carlease.sales.application.port.out.ContractPersistencePort;
import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.springframework.stereotype.Component;

@Component
public class ContractPersistenceAdapter implements ContractPersistencePort {

	private final ContractRepository repository;

	public ContractPersistenceAdapter(ContractRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Contract contract) {
		repository.save(ContractMapper.mapToJpaEntity(contract));
	}

	@Override
	public boolean exists(ContractNumber number) {
		return repository.findById(number.number()).isPresent();
	}

	@Override
	public Contract with(ContractNumber number) {
		ContractJpaEntity contractJpaEntity = repository.findById(number.number()).get();
		return ContractMapper.mapToContract(contractJpaEntity);
	}

}
