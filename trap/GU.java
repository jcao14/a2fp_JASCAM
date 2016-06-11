//GU - GameUtils
//contains some functions the java api should have had

import java.Math;

public class GU {

	public static double sqr(double x) {
		return(x*x);
	}

	public static double distance(double x0, double y0, double x1, double y1) {
		return( Math.sqrt(GU.sqr(y1-y0) + GU.sqr(x1-x0)) ); 
	}

	public static double magnitude(double[] a) {

		double sum = 0.0;
		int i;

		for(i=0; i<a.length; i++) {
			sum += GU.sqr(a[i]);
		}

		return( Math.sqrt(sum) );

	}

	public static void scale(double[] a, double s) {
		
		for(int i=0; i<a.length; i++) {
			a[i]*=s;
		}

	}

	//returns magnitude
	public static double normalize(double[] a) {

		double mag = GU.magnitude(a);
		
		for(int i=0; i<a.length; i++) {
			a[i]/=mag;
		}

		return(mag);

	}

	public static void setMagnitude(double[] a, double s) {

		GU.normalize(a);
		GU.scale(a, s);
		
	}

	//assumes a is 2d vector
	public static double getAngle(double[] a) {
		return( Math.tan(a[1]/a[0]) );
	}
	
	//counterclockwise rotation of 2D vector by degrees of z-axis
	public static double rotate(double[] a, double degrees) {
	    double[] axis = new double[3] {0, 0, 1};
	    double[] base_quat = new double[4] {0,0,1,0};
	    double[] quat = new double[4];
	    double radians = degrees * Math.PI / 180;

	    

        }


}


