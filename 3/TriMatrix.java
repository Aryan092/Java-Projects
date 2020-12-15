/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        // You need to fill in this method.
		super(N, N);
		diag = new double[N];
		upper = new double[N - 1];
		lower = new double[N - 1];
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
		if (i >= diag.length || j>= diag.length) {
			throw new MatrixException("Out of bounds.");
		}
		
		if (i == j) {
			return diag[i];
		} else if (i == j-1) {
			return upper[i];
		} else if (i-1 == j) {
			return lower[j];
		} else {
			return 0.0;
		}
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
        // You need to fill in this method.
		if (i >= diag.length || j>= diag.length) {
			throw new MatrixException("Out of bounds.");
		}
		
		if (i == j) {
			this.diag[i] = val;
		} else if (i == j-1) {
			this.upper[i] = val;
		} else if (i-1 == j) {
			this.lower[j] = val;
		}
		
		//Should there be an exception here for not?
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
		TriMatrix D = decomp();
		double det = 1.0;
		for (int i = 0; i < diag.length; i++) {
			det *= D.diag[i]; // product of the elements along the leading diagonal
		}
		return det;
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        // You need to fill in this method.
		if (n != m) {
			throw new MatrixException("Matrix not a square.");
		}
		
		double max = 0.0;
		for (int i = 0; i < diag.length; i++) {
			if (diag[i] > max) {
				max = diag[i];
			}
		}
		if (max == 0.0) {
			throw new MatrixException("Matrix can't be decomposed.");
		}
		
		if (diag[0] == 0.0) {
			throw new MatrixException("Matrix is singular & hence doesn't have an inverse.");
		}
		
		TriMatrix decomp = new TriMatrix(diag.length);
		
		decomp.diag[0] = diag[0]; //This position doesn't change
		
		for (int i = 0; i < upper.length; i++) {
			decomp.upper[i] = upper[i];
		}
		for (int j = 0; j < lower.length; j++) {
			decomp.lower[j] = lower[j] / decomp.diag[j];
			decomp.diag[j+1] = diag[j+1] - (decomp.lower[j] * decomp.upper[j]);
		}	
		return decomp;
		
	}

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
        // You need to fill in this method.
		if (A.m != diag.length || A.n != diag.length) {
			throw new MatrixException("Matrices are of different sizes.");
		}
		
		TriMatrix Add = new TriMatrix(diag.length);
		for (int i = 0; i < diag.length; i++) {
			for (int j = 0; j < diag.length; j++) {
				Add.setIJ(i, j, (getIJ(i, j) + A.getIJ(i, j)));
			}
		}
		return Add;
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        // You need to fill in this method.
		if (diag.length != A.m) {
			throw new MatrixException("Matrices can't be multiplied.");
		}
		
		GeneralMatrix M = new GeneralMatrix(diag.length, diag.length);
		double [][] total = new double[diag.length][A.n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < A.n; j++) {
				for (int k = 0; k < n; k++) {
					total[i][j] += this.getIJ(i, k) * A.getIJ(k, j);
					M.setIJ(i, j, total[i][j]);
				}
			}
		}
		return M;
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
        // You need to fill in this method.
		TriMatrix product = new TriMatrix(diag.length);
		for (int i = 0; i < diag.length; i++) {
			for (int j = 0; j < diag.length; j++) {
				product.setIJ(i, j, (this.getIJ(i, j) * a));
			}
		}
		return product;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
		for (int i = 0; i < diag.length; i++) {
			diag[i] = Math.random();
		}
		
		for (int j = 0; j < diag.length - 1; j++) {
			lower[j] = Math.random();
			upper[j] = Math.random();
		}
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
		TriMatrix a = new TriMatrix(3);
		a.random();
		Matrix b = new GeneralMatrix(3, 3);
		b.random();
		Matrix d1 = b.multiply(10);
		Matrix d2 = b.multiply(10);
		TriMatrix c = new TriMatrix(4);
		c.diag = new double[] {2, 2, 2, 2};
		c.upper = new double[] {-1, -1, -1};
		c.lower = new double[] {-1, -1, -1};
		
		System.out.println("Tri-Matrix A:\n" +a.toString());
		System.out.println("Matrix B:\n" +b.toString());
		System.out.println("Matrix B x 10:\n" +d1.toString());
		System.out.println("Matrix B * 10:\n" +d2);
		System.out.println("Sum of the Two B+A:\n" +a.add(b));
		System.out.println("Matrix Multiplictation BA:\n" +b.multiply(a));
		System.out.println("Tri-Matrix A:\n" +a.toString());
		System.out.println("Matrix A Decomposed:\n" +a.decomp());
		System.out.println("Determinant of A:\n" +a.determinant());
		System.out.println("");
		System.out.println("Matrix C:\n" +c);
		System.out.println("Matrix C Decomposed:\n" +c.decomp());
		System.out.println("Determinant of C:\n" +c.determinant());
    }
}