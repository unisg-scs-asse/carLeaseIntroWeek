package io.carlease.sales.adapter.out.memory;

import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ContractMemoryAdapter {
	
	private final Map<ContractNumber, Contract> repo;

	private static final Logger logger = LoggerFactory.getLogger(ContractMemoryAdapter.class);
	
	public ContractMemoryAdapter() {
		repo = new HashMap<>();
	}


	public void save(Contract contract) {
		repo.put(contract.number(), contract);
		logger.info("Contract Added to in-memory Map repo. New content of Map Repo:" + repo);

	}


	public boolean exists(ContractNumber number) {
		return repo.containsKey(number);
	}


	public Contract with(ContractNumber number) {
		return repo.get(number);
	}

}
