package io.carlease.sales.adapter.in.web;

import io.carlease.sales.application.port.in.FilloutContractUseCase;
import io.carlease.sales.application.port.in.ApproveContractUseCase;
import io.carlease.sales.application.port.in.ViewContractUseCase;
import io.carlease.sales.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@Controller
public class SalesController {

	private final FilloutContractUseCase filloutContractUseCase;
	private final ViewContractUseCase viewContractUseCase;
	private final ApproveContractUseCase approveContractUseCase;

	public SalesController(
			FilloutContractUseCase filloutContractUseCase,
			ViewContractUseCase viewContractUseCase,
			ApproveContractUseCase approveContractUseCase) {
		this.filloutContractUseCase = filloutContractUseCase;
		this.viewContractUseCase = viewContractUseCase;
		this.approveContractUseCase = approveContractUseCase;
	}

	@GetMapping("/sales/view_contract")
	public Contract viewContract(
			@RequestParam(name = "number", required = false) String number)
	{
		Contract contract = null;
		if (number != null) {
			contract = this.viewContractUseCase.with(ContractNumber.of(number));
		}
		return contract;
	}

	@PostMapping("/sales/fillout_contract")
	public String filloutContract(
			@RequestParam(name = "number") String number,
			@RequestParam(name = "lessee") String lesseeString,
			@RequestParam(name = "car") String carString,
			@RequestParam(name = "price_amount") int priceAmount,
			@RequestParam(name = "price_currency") String priceCurrency)
	{
		this.filloutContractUseCase.with(
				ContractNumber.of(number),
				Customer.of(lesseeString),
				Car.of(carString),
				Amount.of(priceAmount, Currency.valueOf(priceCurrency)));
		return "redirect:/sales/view_contract?number=" + number;
	}

	@PostMapping("/sales/approve_contract")
	public String approveContract(
			@RequestParam(name = "number") String number)
	{
		this.approveContractUseCase.with(
				ContractNumber.of(number),
				SignDate.of(LocalDate.now().getYear(),
						LocalDate.now().getMonth().getValue(),
						LocalDate.now().getDayOfMonth()));
		return "redirect:/sales/view_contract?number=" + number;
	}

}
