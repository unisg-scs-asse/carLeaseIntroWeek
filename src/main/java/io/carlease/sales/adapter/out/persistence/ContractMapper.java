package io.carlease.sales.adapter.out.persistence;

import io.carlease.sales.domain.*;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ContractMapper {

    public static ContractJpaEntity mapToJpaEntity(Contract contract) {
        return new ContractJpaEntity(
                contract.number().number(),
                contract.lessee().customer(),
                contract.car().car(),
                contract.price().amountInCents(),
                contract.price().currency().name(),
                contract.isCalculated() ? contract.leaseTerm().noOfMonths() : null,
                contract.isCalculated() ? contract.interest().perYear() : null,
                contract.isApproved() ? contract.approvalDate().date() : null);
    }

    public static Contract mapToContract(ContractJpaEntity contractJpaEntity) {
        return ContractFactory.restoreContract(
                ContractNumber.of(contractJpaEntity.getNumber()),
                Customer.of(contractJpaEntity.getLessee()),
                Car.of(contractJpaEntity.getCar()),
                Amount.ofCents(contractJpaEntity.getPriceAmount(), Currency.valueOf(contractJpaEntity.getPriceCurrency())),
                contractJpaEntity.getLeaseTermInMonths() != null
                        ? Optional.of(LeaseTerm.ofMonths(contractJpaEntity.getLeaseTermInMonths()))
                        : Optional.empty(),
                contractJpaEntity.getInterestPerYear() != null
                        ? Optional.of(Interest.of(contractJpaEntity.getInterestPerYear()))
                        : Optional.empty(),
                contractJpaEntity.getApprovalDate() != null
                        ? Optional.of(SignDate.of(contractJpaEntity.getApprovalDate()))
                        : Optional.empty());
    }

}
