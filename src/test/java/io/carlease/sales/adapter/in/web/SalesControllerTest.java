package io.carlease.sales.adapter.in.web;

import io.carlease.sales.application.port.in.ApproveContractUseCase;
import io.carlease.sales.application.port.in.FilloutContractUseCase;
import io.carlease.sales.application.port.in.ViewContractUseCase;
import io.carlease.sales.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SalesController.class)
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilloutContractUseCase filloutContractUseCase;

    // the following mocked beans are required because SalesController depends on these use cases, too
    // mock the beans for any other use cases that are referenced in the SalesController
    @MockBean
    private ViewContractUseCase viewContractUseCase;

    @MockBean
    private ApproveContractUseCase approveContractUseCase;

    @Test
    void testFilloutContract() throws Exception {

        // arrange (given)
        willDoNothing().given(filloutContractUseCase).with(
                ContractNumber.of("1"),
                Customer.of("John Buyer"),
                Car.of("Volkswagen ID.3"),
                Amount.of(50000, Currency.valueOf("EUR"))
        );

        // act (when)
        mockMvc.perform(post("/sales/fillout_contract")
                        .param("number", "1")
                        .param("lessee", "John Buyer")
                        .param("car", "Volkswagen ID.3")
                        .param("price_amount", "50000")
                        .param("price_currency", "EUR"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/sales/view_contract?number=1"));

        // assert (then)
        then(filloutContractUseCase).should()
                .with(ContractNumber.of("1"),
                        Customer.of("John Buyer"),
                        Car.of("Volkswagen ID.3"),
                        Amount.of(50000, Currency.valueOf("EUR")));
    }

}
