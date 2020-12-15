import java.util.Scanner;
import java.io.*;
/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    posFirstLast;  // Indicates whether the first and last
                                 // circles overlap or not.
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=472143;
	double [] tempArea = new double [10000]; // temp. array to hold the area of the circles
	Circle [] circleArr = new Circle[10000];
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }

    /**
     * Results function. It should open the file called FileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        // You need to fill in this method
		double x, y, rad, ar; //would the names from other files affect
		circleCounter = 0;
		
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNext()) {		
				x = scanner.nextDouble();
				y = scanner.nextDouble();
				rad = scanner.nextDouble();
			
				//Check for non singular circles as rad represents radii of circles
				if (rad >  Point.GEOMTOL) {
				Circle c = new Circle(x, y, rad);
				ar = c.area();
				tempArea[circleCounter] = ar;
				circleArr[circleCounter] = c;
				circleCounter++;
				}
			
				double [] areaArr = new double [circleCounter];
				for(int i = 0; i < areaArr.length; i++) {
					areaArr[i] = tempArea[i];
				}
				
				maxArea = 0; //since area can't be lower than 0
				for (int i = 0; i < areaArr.length; i++) {
					if (areaArr[i] > maxArea){
					maxArea = areaArr[i];
					}
				}
			
				minArea = Double.MAX_VALUE; //initialised as the largest no. in the array
				for (int i = 0; i < areaArr.length; i++) {
					if (areaArr[i] < minArea){
					minArea = areaArr[i];
					}
				}
				
				medArea = 0; 
				for (int i = 0; i < areaArr.length; i++) {
					if (areaArr.length % 2 == 0) { //if even
						medArea = areaArr[((circleCounter / 2) - 1)] + areaArr[circleCounter / 2];
					} else { //if odd
						medArea = areaArr[(circleCounter - 1)/ 2];
					}
				}
			}
		} catch(Exception e) {
			System.err.println("An error has occured. No file found.");
			e.printStackTrace();
		}
	}
    
	public int posFirstLast() {
		//array starts at 0 hence the last position in the array is (circleCounter - 1)
    	return posFirstLast = circleArr[0].overlap(circleArr[circleCounter - 1]);
    }
	
    /**
     * A function to calculate the avarage area of circles in the array provided. 
     *
     * @param circles  An array if Circles
     */
    public double averageArea(Circle[] circles) {
      // You need to fill in this method and remove the return 0.0 statement.
		double sum = 0;
		
		for (int i = 0; i < circles.length; i++) {
			sum += circles[i].area();
		}
		
		averageArea = sum / circles.length;
		return averageArea;
    }
    
    /**
     * A function to calculate the standard deviation of areas in the circles in the array provided. 
     *
     * @param circles  An array of Circles
     */
    public double areaStandardDeviation(Circle[] circles) {
    // You need to fill in this method and remove the return 0.0 statement.
		double total = 0;
		
		for (int i = 0; i < circles.length; i++) {
			total += Math.pow(circles[i].area(), 2);
		}
		
		stdArea = Math.sqrt(((1 / (double)circles.length) * total) - Math.pow(averageArea(circles), 2));
		return stdArea;
    }                
  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
        // You need to fill in this method.
		Project1 object1 = new Project1();
		Circle circle1 = new Circle(1.0,2.0,3.0);
    	Circle circle2 = new Circle(1.0,2.0,4.0);
    	Circle circle3 = new Circle (1.0,2.0,5.0);
    	Circle circle4 = new Circle (1.0,8.0,3.0);
		Circle[] circles = new Circle[]{circle1, circle2, circle3, circle4};
    	object1.results("Project1.data");
    	System.out.println("maxArea: " +object1.maxArea);
    	System.out.println("minArea: " +object1.minArea);
		System.out.println("medArea: " +object1.medArea);
		System.out.println("posFirstLast: " +object1.posFirstLast());
    	System.out.println("averageArea(): " +object1.averageArea(circles));
		System.out.println("stdArea(): " +object1.areaStandardDeviation(circles));
	}
}

