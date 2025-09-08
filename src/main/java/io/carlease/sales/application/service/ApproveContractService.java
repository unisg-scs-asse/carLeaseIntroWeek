package io.carlease.sales.application.service;

import io.carlease.riskmanagement.application.port.in.AssessApprovedContractPort;
import io.carlease.sales.application.port.in.ApproveContractUseCase;
import io.carlease.sales.application.port.out.ContractPersistencePort;
import io.carlease.sales.domain.ContractNumber;
import io.carlease.sales.domain.SignDate;
import jakarta.transaction.Transactional;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@ApplicationLayer
@Service
@Transactional
public class ApproveContractService implements ApproveContractUseCase {

	private final ContractPersistencePort contractPersistencePort;
	private final AssessApprovedContractPort assessApprovedContractPort;

	public ApproveContractService(ContractPersistencePort contractPersistencePort, AssessApprovedContractPort assessApprovedContractPort) {
		this.contractPersistencePort = contractPersistencePort;
		this.assessApprovedContractPort = assessApprovedContractPort;
	}

	public void with(ContractNumber number, SignDate approvalDate) {
		assert number != null;
		assert approvalDate != null;

		var contract = this.contractPersistencePort.with(number);

		contract.approve(approvalDate);

		this.contractPersistencePort.save(contract);

		assessApprovedContractPort.with(number.number(), approvalDate.year(), approvalDate.month(), approvalDate.day());
	}

}
