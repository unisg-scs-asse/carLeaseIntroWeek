package io.carlease.riskmanagement.domain;

public record ContractNumber(String contractNumber) {

	public static ContractNumber of(String contractNumber) {
		return new ContractNumber(contractNumber);
	}

}
