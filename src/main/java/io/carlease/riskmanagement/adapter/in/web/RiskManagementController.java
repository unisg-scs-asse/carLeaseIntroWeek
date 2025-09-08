package io.carlease.riskmanagement.adapter.in.web;

import io.carlease.riskmanagement.application.service.CheckCreditRatingService;
import io.carlease.riskmanagement.application.service.ListContractsService;
import io.carlease.riskmanagement.application.service.ReadContractService;
import io.carlease.riskmanagement.application.service.VoteContractService;
import io.carlease.riskmanagement.domain.ContractNumber;
import io.carlease.riskmanagement.domain.CreditRating;
import io.carlease.riskmanagement.domain.VoteResult;
import io.carlease.riskmanagement.domain.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class RiskManagementController {

	private final ListContractsService listContractsService;
	private final ReadContractService readContractService;
	private final CheckCreditRatingService checkCreditRatingService;
	private final VoteContractService voteContractService;

	public RiskManagementController(
			ListContractsService listContractsService,
			ReadContractService readContractService,
			CheckCreditRatingService checkCreditRatingService,
			VoteContractService voteContractService) {
		this.listContractsService = listContractsService;
		this.readContractService = readContractService;
		this.checkCreditRatingService = checkCreditRatingService;
		this.voteContractService = voteContractService;
	}

	@GetMapping("/riskmanagement/list_contracts")
	public List<Contract> listContracts()
	{
        return this.listContractsService.all().stream().toList();
	}

	@GetMapping("/riskmanagement/show_contract")
	public Contract showContract(
			@RequestParam(name= "number", required = false) String number)
	{
		Contract contract = null;
		if (number != null) {
			contract = this.readContractService.readContract(ContractNumber.of(number));
		}
		return contract;
	}

	@PostMapping("/riskmanagement/rate_contract")
    public String enterCreditRating(
            @RequestParam(name= "number") String number,
            @RequestParam(name= "creditRating") Integer creditRating)
	{
		if (number != null && creditRating != null && CreditRating.isValid(creditRating)) {
			this.checkCreditRatingService.checkCreditRating(
					ContractNumber.of(number),
					CreditRating.of(creditRating));
		}
		return "redirect:/riskmanagement/show_contract?number=" + number;
    }

    @PostMapping("/riskmanagement/vote_contract")
    public String voteContract(
            @RequestParam(name= "number") String number,
            @RequestParam(name="vote_result") String voteResult)
	{
        try {
            VoteResult.valueOf(voteResult);
        } catch(IllegalArgumentException e) {

		}
        this.voteContractService.vote(
                ContractNumber.of(number),
                VoteResult.valueOf(voteResult));
        return "redirect:/riskmanagement/show_contract?number=" + number;
    }

}
