/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
		int n = coeff.length;
		for (int i = (n - 1); i >= 0; i--) {
			if ((coeff[i].getReal() == 0) && (coeff[i].getImag() == 0)) {
				n--;
			} else {
				break;
			}
		}
		
		this.coeff = new Complex[n];
		for(int i = 0; i < n; i++) {
				this.coeff[i] = coeff[i];
		}
	}
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
		Complex zeroP = new Complex(0.0, 0.0);
		coeff[0] = zeroP;
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        // You need to fill in this function.
		String s = "";
		s = "(" +coeff[0].toString()+ ")";
		for (int i = 1; i < coeff.length; i++){
			if (coeff.length == 1) {
				s = s + "+ (" +coeff[1].toString()+ ")X";
			} else {
				s = s + "+ (" +coeff[i].toString()+ ")X^"+Integer.toString(i);
			}
		}
		
		return s;
	}

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
		return (this.coeff.length - 1);
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
		//double evaluate = (double)coeff[0];
		int length = this.coeff.length;
		Complex evaluate = this.coeff[length - 1];
		for (int i = length - 2; i >= 0; i--){
			evaluate = this.coeff[i].add(z.multiply(evaluate)) ;
		}
		return evaluate;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
        // You need to fill in this function.
		Complex[] der = new Complex[coeff.length - 1];
		if (der.length == 0) {
			return new Polynomial();
		} 
		for (int i = 0; i < der.length; i++){
			der[i] = this.coeff[i+1].multiply(i+1);
		}
		
		return new Polynomial(der);
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You need to fill in this function.
		Complex A = new Complex(1.0, 1.0);
		Complex B = new Complex(2.0, 4.0);
		Complex C = new Complex(0.0, 8.0);
		Complex Z = new Complex(3.0);

		Complex[] polyarray = {A,B,C};

		Polynomial newPolynomial = new Polynomial(polyarray);
		System.out.println("Polynomial: "+ newPolynomial.toString());
		System.out.println("Degree: "+ newPolynomial.degree());
		System.out.println("Derivative is:"+newPolynomial.derivative().toString());
		System.out.println("At z = 3: "+newPolynomial.evaluate(Z));
		
    }
}