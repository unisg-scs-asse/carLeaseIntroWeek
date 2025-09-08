package io.carlease.sales.adapter.in.vaadin;

import io.carlease.sales.application.port.in.FilloutContractUseCase;
import io.carlease.sales.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesVaadinController {
    @Autowired
    private FilloutContractUseCase filloutContractUseCase;

    public SalesVaadinController(FilloutContractUseCase filloutContractUseCase) {
        this.filloutContractUseCase = filloutContractUseCase;
    }

    public void saveContract(String number, String customer, String car, Double price, String currency) {
        try {
            this.filloutContractUseCase.with(
                    ContractNumber.of(number),
                    Customer.of(customer),
                    Car.of(car),
                    Amount.of(price, Currency.valueOf(currency)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
