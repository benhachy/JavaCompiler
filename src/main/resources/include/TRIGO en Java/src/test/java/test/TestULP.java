package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.MathDeca;



public class TestULP {
	
	@Test
	public void testULP() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = 100*Math.random();
			//when 
			double actual = MathDeca.ulp(entry);
			
			//then 
			double expected = Math.ulp(entry);
			assertEquals(expected,actual,1E-5);
		}
	}
	
}
