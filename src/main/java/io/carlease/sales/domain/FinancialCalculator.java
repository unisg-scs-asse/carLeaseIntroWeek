package io.carlease.sales.domain;

import org.jmolecules.ddd.annotation.Service;

/**
 * Simulates the infamous HP12c calculator that is widely used in the leasing industry.
 */
@Service
public class FinancialCalculator {

    /**
     * @param n number of periods
     * @param iInPercent percentage interest rate per period
     * @param pv present value (aka lease amount)
     * @param fv future value (aka residual value)
     * @param a advance payment (aka down payment)
     * @return payment per period
     */
	public static double pmt(double n, double iInPercent, double pv, double fv, double a) {
		double i = iInPercent / 100.0;

        return pmtWithDecimalInterestRate(n, i, pv, fv, a);
	}

    /**
     * @param n number of periods
     * @param i decimal interest rate per period
     * @param pv present value (aka lease amount)
     * @param fv future value (aka residual value)
     * @param a advance payment (aka down payment)
     * @return payment per period
     */
    private static double pmtWithDecimalInterestRate(double n, double i, double pv, double fv, double a) {
        if (i == 0.0) {
            return (-1.0 * pv - fv) / n;
        }

		return (i * (fv + pv * Math.pow(1.0 + i, n))) / ((1.0 + i * a) * (1.0 - Math.pow(1.0 + i, n)));
    }

}
