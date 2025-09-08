package io.carlease.sales.domain;

import org.jmolecules.ddd.annotation.Factory;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Factory
public class ContractFactory {

	public static Contract restoreContract(ContractNumber number, Customer lessee, Car car, Amount price, Optional<LeaseTerm> leaseTerm, Optional<Interest> interest, Optional<SignDate> approvalDate) {
		requireNonNull(number);
		requireNonNull(lessee);
		requireNonNull(car);
		requireNonNull(price);

		var contract = new Contract(number, lessee, car, price);
        if (leaseTerm.isPresent() && interest.isPresent()) {
            contract.calculateInstallmentFor(leaseTerm.get(), interest.get());
        }
        approvalDate.ifPresent(contract::approve);

		return contract;
	}

}
