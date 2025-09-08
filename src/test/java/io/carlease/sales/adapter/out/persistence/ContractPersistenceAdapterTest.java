package io.carlease.sales.adapter.out.persistence;

import io.carlease.sales.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ContractPersistenceAdapter.class)
public class ContractPersistenceAdapterTest {

    @Autowired
    private ContractPersistenceAdapter contractPersistenceAdapter;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    @Sql("ContractPersistenceAdapterTest.sql")
    void loadsContract() {
        Contract contract = contractPersistenceAdapter.with(new ContractNumber("1"));

        assertThat(contract.lessee()).isEqualTo(new Customer("John Buyer"));
        assertThat(contract.car()).isEqualTo(new Car("Volkswagen ID.3"));
        assertThat(contract.price()).isEqualTo(Amount.of(50000, Currency.EUR));
    }

    @Test
    void savesContract() {
        Contract contract = new Contract(ContractNumber.of("1"),
                Customer.of("John Buyer"),
                Car.of("Volkswagen ID.3"),
                Amount.of(50000, Currency.valueOf("EUR")));

        contractPersistenceAdapter.save(contract);

        assertThat(contractRepository.count()).isEqualTo(1);
    }

}
