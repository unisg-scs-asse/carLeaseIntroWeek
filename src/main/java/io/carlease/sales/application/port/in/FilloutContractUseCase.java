package io.carlease.sales.application.port.in;

import io.carlease.sales.domain.Amount;
import io.carlease.sales.domain.Car;
import io.carlease.sales.domain.ContractNumber;
import io.carlease.sales.domain.Customer;

public interface FilloutContractUseCase {

    void with(ContractNumber number, Customer customer, Car car, Amount price);
}
