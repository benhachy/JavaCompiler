package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.ForMath;

public class TestRacine {

	@Test
	public void testRacine() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = 100*Math.random();
			//when 
			double actual = ForMath.racine(entry);
			
			//then 
			double expected = Math.sqrt(entry);
			assertEquals(expected,actual,1E-14);
		}
	}
}
