/*
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */
	 
	private Point A;
	private double r;
	public static final double GEOMTOL = 1.0e-6;
	
    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        // You need to fill in this method.
		this.A = new Point(xc, yc);
		this.r = rad;
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param center  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point center, double rad) {
        // You need to fill in this method.
		this.A = center;
		this.r = rad;
		
    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCenter(double xc, double yc) {
        // You need to fill in this method.
		this.A = new Point(xc, yc);
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param p  A Point representing the new centre of the circle.
     */   
    public void setCenter(Point p) {
        // You need to fill in this method.
		this.A = p;
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        // You need to fill in this method.
		this.r = rad;
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCenter(){
        // You need to fill in this method.
		return A;
	}

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        // You need to fill in this method.
		return r;
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        // You need to fill in this method.
		return A.toString() + ", r = " + r;
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     * 
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        // You need to fill in this method.
            return ((Math.abs(r - c.getRadius()) <= GEOMTOL) && this.A.equals(c.getCenter()));
    }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        // You need to fill in this method.
		double ar;
		ar = r * r * Math.PI;
		return ar;
    }

    /**
     * Tests whether this circle overlaps/touches/is disjoint with another
     * Circle, as set out in the project formulation.
     *
     * @return An integer describing the overlap with C:
     *         0 - disjoint; 1 - overlaps; 2 - touches; 3 - identical.
     */
    public int overlap(Circle C) {
        // You need to fill in this method.
		// Disjoint -> d(C1, C2) > r1 + r2
		// Overlap -> d(C1, C2) < r1 + r2
		// At one point -> (d(C1, C2) - r1 + r2) <= GEOMTOL
		int i = 0;
		if (this.A.distance(C.getCenter()) < this.getRadius() + C.getRadius()) {
			i = 1;
			return i;
		} else if(Math.abs(this.A.distance(C.getCenter()) - this.getRadius() - C.getRadius()) <= GEOMTOL) {
			i = 2;
			return i;
		} else if(this.equals(C)) {
			i = 3;
			return i;
		} else {
			return i;
		}
    }


    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        // You need to fill in this method.
		Circle circle = new Circle(1.0,2.0,3.0);
		System.out.println(circle.area());
    	Circle circle2 = new Circle(1.0,2.0,3.0);
    	System.out.println(circle.equals(circle2));
    	Circle circle3 = new Circle (1.0,2.0,3.0);
    	Circle circle4 = new Circle (1.0,8.0,3.0);
    	int i = 0;
    	i = circle3.overlap(circle4);
    	System.out.println(i);
    	System.out.println(circle.A.distance(circle2.A));
    }
}
