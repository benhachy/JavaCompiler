package math;


public class MathDeca {

	public final static int MAX_EXPONENT = 127;
	public final static int MIN_EXPONENT = -126;
	public final static double MIN_VALUE = 1.4E-45;
	public final static int SIGNIFICAND_WIDTH = 24;
	
	public static double ulp(double d) {
		int exp = ForMath.getExponent(d);
		double returnValue = 0.0;
		 if (exp == MAX_EXPONENT + 1) {
			 returnValue = ForMath.abs(d); 
		}
		 else if (exp == MIN_EXPONENT -1 ) {
			 returnValue = MIN_VALUE;
		 }
		 
		 else {
			 assert exp <= MAX_EXPONENT && exp >= MIN_EXPONENT;
			 exp = exp - (SIGNIFICAND_WIDTH - 1);
			 
			 if(exp >= MIN_EXPONENT) {
				 returnValue = ForMath.puissanceDeDeux(exp);
			 }
			 
			 else {
				 returnValue = 1 << (exp - (MIN_EXPONENT - (SIGNIFICAND_WIDTH - 1))); 
			 }
		 }
		 
		 return returnValue;
		 
		 }
	
	
public static double cos(double theta) {
		
		theta = ForMath.anglePrincipal(theta); // on retrouve l'angle principale càd compris entre -PI et PI de theta
		
		if (theta > ForMath.PI/2 ) {
			theta -= ForMath.PI/2;
			return - sin(theta);
		}
		
		else if (theta < - ForMath.PI/2) {
			theta = - ForMath.PI/2 - theta;
			return -sin(theta);
		}
		
		else {
			final double K = 1.646760258121;
			int n = 45;
			
			double x = 1/K;
			double y = 0;
			double z = theta;
			
			int i = 0;
			while ( i < n) {
				int d;
				
				double funx = x;
				double funy = y;
				double funz = z;
				
				// Initialisation de d	
				if (funz > 0) {
					d = 1;
				}
				else {
					d = -1;
				}
				
				x = funx - d * funy * ForMath.puissanceDeDeux(-i);
				y = funy + d * funx * ForMath.puissanceDeDeux(-i);
				z = funz - d * Math.atan( ForMath.puissanceDeDeux(-i));
				
				i++;
			}
			return x;	
		}	
	}
	
	
	public static double sin(double theta) {
		
		theta = ForMath.anglePrincipal(theta); // on retrouve l'angle principale càd compris entre -PI et PI de theta

		
		if (theta > ForMath.PI/2 ) {
			theta -= ForMath.PI/2;
			return cos(theta);
		}
		
		else if(theta < - ForMath.PI/2 ) {
			theta += ForMath.PI/2;
			return -cos(theta);
		}
		
		else {
			final double K = 1.646760258121;
			int n = 45;
			
			double x = 1/K;
			double y = 0;
			double z = theta;
			
			int i = 0;
			while ( i < n) {
				int d;
				
				double funx = x;
				double funy = y;
				double funz = z;
				
				// Initialisation de d	
				if (funz > 0) {
					d = 1;
				}
				else {
					d = -1;
				}
				
				x = funx - d * funy * ForMath.puissanceDeDeux(-i);
				y = funy + d * funx * ForMath.puissanceDeDeux(-i);
				z = funz - d * Math.atan( ForMath.puissanceDeDeux(-i));
				
				i++;
			}
			
			return y;
		}
		
		
	}
	
	public static double atan(double val) {
		
		
		// Le if else statement sert à adapter pour le cas où abs(val) >1
		
		if(val > 1) {
			return ForMath.PI/2 - atan(1/val);
		}
		
		else if (val < -1) {
			return - ForMath.PI/2 - atan(1/val);
		}
		
		int n = 45;
		
		double x = 1;
		double y = val;
		double z = 0;
		
		int i = 0;
		
		while ( i < n) {
			int d;
			
			double funx = x;
			double funy = y;
			double funz = z;
			
			// Initialisation de d	
			if (funy > 0) {
				d = -1;
			}
			else {
				d = 1;
			}
			
			x = funx - d * funy * ForMath.puissanceDeDeux(-i);
			y = funy + d * funx * ForMath.puissanceDeDeux(-i);
			z = funz - d * Math.atan( ForMath.puissanceDeDeux(-i));
		
			i++;
		}
		
		return z;
		
	}
	
	public static double asin(double val) {
		
		if (val > 1 || val < -1) {
			return Double.NaN; // En Deca, si le type Nan n'existe pas, on fera un try catch, si ça n'existe pas, on ignore le cas
		}
		
		else if (val == 1){
			return ForMath.PI/2;
		}
		
		else if (val == -1) {
			return - ForMath.PI/2;
		}
		
		else {
			val = val/ForMath.racine((1 - val * val));
			return atan(val);
		}
	}
	
	
}
