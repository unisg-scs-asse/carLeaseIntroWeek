package io.carlease.sales.application.port.in;

import io.carlease.sales.domain.*;

public interface ViewContractUseCase {

    Contract with(ContractNumber number);
}
