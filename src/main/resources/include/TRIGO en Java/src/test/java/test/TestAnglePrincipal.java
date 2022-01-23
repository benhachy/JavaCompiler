package test;

import static java.lang.Math.PI;
import math.ForMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAnglePrincipal {
	
	// On peut aussi faire un graphe pour montrer que la fonction est correcte (continue par morceaux)
	@Test 
	void testAnglePrincipal() {
		// given 
		double theta1 =  PI/8 + 6*PI;
		double theta2 =  - PI/8 - 4*PI;
		double theta3 =  3*PI/4 + 4*PI;
		double theta4 = - 3*PI/4 - 6*PI;
		
		// when 
		double actual1 = ForMath.anglePrincipal(theta1); 
		double actual2 = ForMath.anglePrincipal(theta2); 
		double actual3 = ForMath.anglePrincipal(theta3); 
		double actual4 = ForMath.anglePrincipal(theta4); 
		
		// then
		
		double expected1 = PI/8;
		double expected2 = - PI/8;
		double expected3 = 3*PI/4;
		double expected4 = -3*PI/4;

		assertEquals(expected1, actual1, 1E-12);
		assertEquals(expected2, actual2, 1E-12);
		assertEquals(expected3, actual3, 1E-12);
		assertEquals(expected4, actual4, 1E-12);
		
	}
}
