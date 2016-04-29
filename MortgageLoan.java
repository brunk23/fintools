/** MortgageLoan
 * A class used to create mortgage loans
 */
package test;

/** Create a Mortgage Loan
 * Given the basic terms of a loan, this will contain
 * the estimated payment and total interest over the life
 * of the loan.
 * 
 * @author Kevin Brunelle
 * @author brunellek@gmail.com
 * 
 * @version 1.0
 */
public class MortgageLoan {
	private double amount;
	private double rate;
	private int term;		// The number of months
	private double totalInterest;
	private double payment;
	private boolean calculated;

	/** Create a Mortgage Loan
	 * 
	 * @param a Amount Borrowed
	 * @param r Rate per Year
	 * @param t Term in Months
	 */
	public MortgageLoan(double a, double r, int t) {
		amount = a;
		rate = r;
		term = t;
	}
	
	/** Get Monthly Payment
	 * 
	 * @return Monthly Payment
	 */
	public double getPayment() {
		if( !calculated ) {
			calculate();
		}
		return payment;
	}
	
	/** Get Total Interest Cost for Loan
	 * 
	 * @return Total Interest
	 */
	public double getInterest() {
		if( !calculated ) {
			calculate();
		}
		return totalInterest;
	}
	
	/** Public Access Function for Interest Rate
	 * 
	 * @return Interest Rate
	 */
	public double getRate() {
		return rate;
	}
	
	/** Public Access Function for Amount Borrowed
	 * 
	 * @return Amount Borrowed
	 */
	public double getAmount() {
		return amount;
	}
	
	/** Public Access Function for Length of Loan
	 * 
	 * @return Term in Months
	 */
	public int getTerm() {
		return term;
	}

	/** Perform the Calculations
	 * 
	 * Generate the estimated monthly payment and the
	 * total estimated interest if the loan is paid on
	 * schedule.
	 */
	private void calculate() {
		if( (amount > 0) && (rate >= 0) && (term > 0) ) {
			
			// The following lines implement the amortization formula
			payment = (rate / 12) * Math.pow( 1 + rate / 12, term);
			payment /= (Math.pow( 1 + rate / 12, term) - 1);
			payment *= amount;
			
			// Total interest will be the sum of all the payments
			// minus the original amount borrowed
			totalInterest = (payment * term) - amount;
			
			// The calculations have been done and are current
			calculated = true;
		} else {
			
			// One of the three values was not valid. So, we do
			// not set calculated, and we make the generated values
			// an obviously incorrect value
			calculated = false;
			totalInterest = -1;
			payment = -1;
		}
	}
	
}
