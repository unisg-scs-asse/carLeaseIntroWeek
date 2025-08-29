package io.carlease.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

import java.time.LocalDate;

@ValueObject
public record SignDate(LocalDate date) {

	public static SignDate of(LocalDate date) {
		assert date != null;
		return new SignDate(date);
	}

	public static SignDate of(int year, int month, int dayOfMonth) {
		return of(LocalDate.of(year, month, dayOfMonth));
	}

	public int year() {
		return date.getYear();
	}

	public int month() {
		return date.getMonthValue();
	}

	public int day() {
		return date.getDayOfMonth();
	}

}
