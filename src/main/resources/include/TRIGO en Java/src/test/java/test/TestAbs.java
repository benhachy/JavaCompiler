package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.ForMath;

public class TestAbs {
	
	@Test
	public void testAbs() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry =   100*Math.random();
			//when 
			double actual = ForMath.abs(entry);
			
			//then 
			double expected = Math.abs(entry);
			assertEquals(expected,actual,1E-14);
		}
	}
	

}
