package io.carlease.riskmanagement.domain;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Contract extends io.hschwentner.dddbits.basetype.Entity<ContractNumber>  {

    @Identity
    private final ContractNumber number;

	private CreditRating creditRating;
	private VoteResult voteResult;

	public Contract(ContractNumber number, SignDate approvalDate) {
		super(number);
        assert number != null;
		assert approvalDate != null;

        this.number = number;
	}

	//TODO: @Factory
	public static Contract restore(ContractNumber number, SignDate approvalDate, CreditRating rating, VoteResult voteResult) {
		assert number != null;
		assert approvalDate != null;
//		assert voteResult == null || rating != null    // VoteResult != null => rating != null

		var restoredContract = new Contract(number, approvalDate);
		restoredContract.creditRating = rating;
		restoredContract.voteResult = voteResult;
		return restoredContract;
	}

    @Identity
    public ContractNumber number() {
        return this.number;
    }

	public void checkCreditRating(CreditRating creditRating) {
		assert creditRating != null;
		assert !isVoted();

		this.creditRating = creditRating;

		assert isRated();
	}

	public boolean isRated() {
		return creditRating != null;
	}

	public CreditRating rating() {
		assert isRated() : "Precondition violated: isRated()";

		return creditRating;
	}

	public void vote(VoteResult result) {
		assert result != null;
		assert isRated();

		this.voteResult = result;

		assert isVoted();
	}

	public boolean isVoted() {
		return voteResult != null;
	}

	public VoteResult votingResult() {
		assert isVoted();

		return this.voteResult;
	}

    // TODO: equals() and hashCode()

}
