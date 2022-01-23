package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.ForMath;

public class TestPuissanceDeDeux {

	@Test
	public void testPuissanceDeDix() {
		
		for (int i = -10; i <= 25; i++) {
			//given 
			int entry = i;
			//when 
			double actual = ForMath.puissanceDeDeux(entry);
			
			//then 
			double expected = Math.pow(2,entry);
			assertEquals(expected,actual,1E-12);
		}
	
	}
}
