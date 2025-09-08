package io.carlease.sales.application.port.in;

import io.carlease.sales.domain.*;

public interface ApproveContractUseCase {

    void with(ContractNumber number, SignDate approvalDate);
}
