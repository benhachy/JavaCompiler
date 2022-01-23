package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.MathDeca;

public class TestAtan {

	@Test
	public void testAtan() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = 100*Math.random();
			//when 
			double actual = MathDeca.atan(entry);
			
			//then 
			double expected = Math.atan(entry);
			assertEquals(expected,actual,1E-13);
		}
	}
}
