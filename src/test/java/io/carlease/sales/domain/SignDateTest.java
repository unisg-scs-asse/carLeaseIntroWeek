package io.carlease.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SignDateTest {

	@Test
	void test() {
		// when
		SignDate signDate1 = SignDate.of(2018, 8, 4);
		SignDate signDate2 = SignDate.of(2018, 8, 4);
		
		// then
		assertThat(signDate1).isEqualTo(signDate2);
	}

}
