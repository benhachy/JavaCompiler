package math;

public class ForMath {

public static final double PI = 3.141592653589793 ;
	
	public static double anglePrincipal(double theta) {
		
		while (theta > 2 * PI ) {
			theta -= 2 * PI;
		}
		
		while (theta < 0) {
			theta += 2 * PI;
		}
		
		if (theta > PI) {
			theta -= 2*PI ;
		}
		
		return theta;
	}
	
	/**
	 * Cette algorithme aprs devient trs lent dans le cas suivant par exemple (1009,28)
	 * Ds que le nombre d'itration devient plus grand que 25 
	 * 
	 * Les entres sont pour < 100 ;
	 * */
	public static double racine_n(double a, int n) {
		if (n == 0 ) {
			return -190/(a+20) + 10;
		}
		else {
			return   0.5*(racine_n(a, n -1) + a/racine_n(a, n -1) );
		}
	}
	
	// On peut pas regrouper les deux fonctions  cause de la rccurence 
	public static double racine(double a) {
		
		if(a < 0) {
			return Double.NaN;
		}
		
		return racine_n(a, 5);
	}
	
	public static double puissanceDeDeux(int i) {
		if(i < 0) {
			i = -i;
			double count = 1;
			for (int j = 0; j < i; j++) {
				count *= 0.5;
			}
			return count;
		}
		
		else {
			double count = 1;
			for (int j = 0; j < i; j++) {
				count *= 2;
			}
			return count;
		}
		
	}
	
	public static double abs(double f ) {
		if (f >= 0) {
			return f;
		}
		else {
			return -f;
		}
	}
	
	
	/**
	 * Cette fonction retourn l'exposant mais en mode decimal 
	  	
	*/
	
	public static int getExponent(double d ) {
		int nbrMaxDeDeux = -1023;
		
		if(d != 0) {
			nbrMaxDeDeux = nbrMaxDeDeux(d);
		}
		
		return nbrMaxDeDeux;
	}
	
	
	public static int nbrMaxDeDeux(double d ) {
		d = abs(d);
		int m = 0;
		
		while (d >= 2) {
			m += 1;
			d /= 2;
		}
		return m;
	}
	
	public static int nbrDeDeux(int nbrDeDix) {
		int nbrDeDeux = -127;
		if (nbrDeDix > 0 ) {
			int r = nbrDeDix % 3;
			int q = (nbrDeDix - r) / 3 ;
			
			if (r == 0) {
				nbrDeDeux =  9 + (q-1) * 10;
			}
			
			else if (r == 1) {
				nbrDeDeux = 3 + q * 10;
			}
			
			else {
				nbrDeDeux =  6 + q *10;
			}
		}
		
		else if (nbrDeDix < 0) {	
			nbrDeDix = -nbrDeDix;
			return -nbrDeDeux(nbrDeDix) - 1;
		}
		
		return nbrDeDeux;
		
		
		
	}	
	
	}
