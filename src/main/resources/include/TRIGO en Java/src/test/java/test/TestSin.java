package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.MathDeca;

public class TestSin {

	@Test
	public void testSin() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = 100*Math.random();
			//when 
			double actual = MathDeca.sin(entry);
			
			//then 
			double expected = Math.sin(entry);
			assertEquals(expected,actual,1E-13);
		}
	}
}
