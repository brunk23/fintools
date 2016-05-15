package test;

/**
 * Ratio numbers, provide support for fractions
 * @author Kevin Brunelle
 * @author brunellek@gmail.com
 * 
 */
public class Ratio {
	private long denom;
	private long numer;

	/**
	 * Test if is less than
	 */
	public boolean isLessThan(Ratio x) {
		Ratio n = subtract(x);
		return (n.getNumerator() < 0);
	}
	
	/**
	 * Test if less than for whole numbers
	 * @param x
	 * @return
	 */
	public boolean isLessThan(long x) {
		Ratio n = new Ratio(x);
		return isLessThan(n);
	}

	/**
	 * Test if greater than for other fractions
	 * @param x
	 * @return
	 */
	public boolean isGreaterThan(Ratio x) {
		Ratio n = subtract(x);
		return (n.getNumerator() > 0);
	}
	
	/**
	 * Test if greater than for whole numbers
	 * @param x
	 * @return
	 */
	public boolean isGreaterThan(long x) {
		Ratio n = new Ratio(x);
		return isGreaterThan(n);
	}
	
	/**
	 * Test if greater than or equal to fraction
	 * @param x
	 * @return 
	 */
	public boolean isGTE(Ratio x) {
		return !(isLessThan(x));
	}
	
	/**
	 * Test if greater than or equal to whole number
	 * @param x
	 * @return 
	 */
	public boolean isGTE(long x) {
		return !(isLessThan(x));
	}
	
	/**
	 * Test if less than or equal to fraction
	 * @param x
	 * @return 
	 */
	public boolean isLTE(Ratio x) {
		return !(isGreaterThan(x));
	}
	
	/**
	 * Test if less than or equal to whole number
	 * @param x
	 * @return 
	 */
	public boolean isLTE(long x) {
		return !(isGreaterThan(x));
	}
	
	/**
	 * Test if ratio is equivalent to a whole number.
	 * @param x
	 * @return
	 */
	public boolean equals(long x) {
		Ratio n = new Ratio(x);
		return equals(n);
	}
	
	/**
	 * Test if two ratios have the same value
	 * @param x
	 * @return equality
	 */
	public boolean equals(Ratio x) {
		Ratio n = subtract(x);
		return (n.getNumerator() == 0);
	}
	
	/**
	 * Test if this is a whole number or not.
	 * @return true if a whole number, else false
	 */
	public boolean isWholeNumber() {
		simplify();
		return (denom == 1);
	}
	
	/**
	 * Test if this is a fraction
	 * @return true if not a whole number, else false
	 */
	public boolean isFraction() {
		return !(isWholeNumber());
	}
	
	/**
	 * Used during testing, prints fraction to stdout
	 */
	public void print() {
		System.out.println(numer + "/" + denom);
	}
	
	/**
	 * This will return the formatted string representation
	 * for printing purposes.
	 * @return formatted string representation
	 */
	public String toString() {
		return (numer + "/" + denom);
	}
	
	/**
	 * Find the product of two ratios
	 * @param multiplicand
	 * @return product
	 */
	public Ratio multiply(Ratio x) {
		Ratio n = new Ratio();
		n.setDenominator(denom * x.getDenominator());
		n.setNumerator(numer * x.getNumerator());
		n.positiveDenom();
		n.simplify();
		return n;
	}
	
	/**
	 * Uses the multiply method to return the product
	 * @param long x
	 * @return product
	 */
	public Ratio multiply(long x) {
		Ratio n = new Ratio(x);
		return multiply(n);
	}
	
	/**
	 * Uses the reciprocal of the long number and then the
	 * multiply method to return the quotient
	 * @param divisor
	 * @return quotient
	 */
	public Ratio divide(long x) {
		Ratio n = new Ratio(1, x);
		return multiply(n);
	}
	
	/**
	 * Uses the reciprocal of the fraction and then the multiply
	 * method to return the quotient
	 * @param divisor
	 * @return quotient
	 */
	public Ratio divide(Ratio x) {
		Ratio n = new Ratio( x.getDenominator(), x.getNumerator());
		return multiply(n);
	}
	
	/**
	 * This is the main method that does all the addition and
	 * subtraction work for this class.
	 * @param Ratio addend
	 * @return sum
	 */
	public Ratio add(Ratio x) {
		Ratio n = new Ratio();
		n.setDenominator(denom * x.getDenominator());
		n.setNumerator(numer * x.getDenominator() + 
				x.getNumerator() * denom);
		n.positiveDenom();
		n.simplify();
		return n;
	}
	
	/**
	 * Turns the long into a Ratio and then uses the add method
	 * to calculate the sum.0
	 * @param long addend
	 * @return sum
	 */
	public Ratio add(long x) {
		Ratio n = new Ratio(x);
		return add(n);
	}
	
	/**
	 * Uses the add method to find the difference by adding the
	 * inverse of the long value
	 * @param long subtrahend
	 * @return this - subtrahend
	 */
	public Ratio subtract(long x) {
		return add(-x);
	}
	
	/**
	 * Uses the add method to find the difference by adding the
	 * inverse.
	 * @param Ratio subtrahend
	 * @return this - subtrahend
	 */
	public Ratio subtract(Ratio x) {
		Ratio n = new Ratio(-x.getNumerator(),x.getDenominator());
		return add(n);
	}
	
	/**
	 * Set the numerator of a ratiotic number
	 * @param x
	 */
	public void setNumerator(long x) {
		numer = x;
	}
	
	/**
	 * Set the denominator of a ratiotic number
	 * @param x (can't be 0: 0 will become a 1)
	 */
	public void setDenominator(long x) {
		if( x != 0 ) {
			denom = x;
		} else {
			denom = 1;
		}
	}
	
	/**
	 * Return the value of the numerator
	 * @return numerator
	 */
	public long getNumerator() {
		return numer;
	}
	
	/**
	 * Return the value of the denominator
	 * @return denominator
	 */
	public long getDenominator() {
		return denom;
	}
	
	/**
	 * Internal function to find the greatest common
	 * factor for the purposes of simplifying the ratio.
	 * @return gcf
	 */
	private long gcf(){
		long a = numer, b = denom, t;
		
		// Euclid's algo
		while( b != 0 ) {
			t = b;
			b = a % b;
			a = t;
		}
		return Math.abs(a);
	}
	
	/**
	 * Internal function that will simplify the fraction
	 * by dividing the numerator and denominator by their
	 * greatest common factor.
	 */
	private void simplify() {
		long y = gcf();
		denom /= y;
		numer /= y;
	}
	
	/**
	 * Check if the denominator is negative, if it is then
	 * multiply by -1/-1 to make it positive.
	 */
	private void positiveDenom() {
		if( denom < 0 ) {
			denom *= -1;
			numer *= -1;
		}
	}
	
	/**
	 * Default constructor makes a fraction of 0/1.
	 */
	public Ratio() {
		denom = 1;
		numer = 0;
	}
	
	/**
	 * Construct a whole number fraction of x/1.
	 * @param x
	 */
	public Ratio(long x) {
		denom = 1;
		numer = x;
	}
	
	/**
	 * Construct a fraction of x/y, y can't be 0
	 * If y is 0, it will be turned into a 1.
	 * @param x
	 * @param y
	 */
	public Ratio(long x, long y) {
		if( y != 0 ) {
			denom = y;
		} else {
			// This should be an error
			denom = 1;
		}
		numer = x;
	}
}
