package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.ForMath;

public class TestGetExponent {

	@Test
	public void testGetExponent() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry =   100*Math.random();
			//when 
			int actual = ForMath.nbrMaxDeDeux(entry);
			
			//then 
			int expected;
			 
			if (entry <= 1) {
				expected = 0;
			}
			else {
				expected = Math.getExponent(entry);

			}
			assertEquals(expected,actual);
		}
	}
}
