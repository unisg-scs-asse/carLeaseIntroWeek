package io.carlease.riskmanagement.domain;

import java.time.LocalDate;

public record SignDate(LocalDate date) {

	public static SignDate of(int year, int month, int dayOfMonth) {
		return new SignDate(LocalDate.of(year, month, dayOfMonth));
	}

}
