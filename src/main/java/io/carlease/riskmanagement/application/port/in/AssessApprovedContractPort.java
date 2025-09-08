package io.carlease.riskmanagement.application.port.in;

//@Component
public interface AssessApprovedContractPort {

	void with(String number, int year, int month, int dayOfMonth);

}