package test;

import java.lang.Math;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.MathDeca;

public class TestAsin {

	@Test
	public void testAsin() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = Math.random();
			//when 
			double actual = MathDeca.asin(entry);
			
			//then 
			double expected = MathDeca.asin(entry);
			assertEquals(expected,actual,1E-13);
		}
	}
}
