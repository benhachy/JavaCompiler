package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.MathDeca;

import java.lang.Math;

public class TestCos {

	@Test
	public void testCos() {
		for (int i = 0; i <= 100; i++) {
			//given 
			double entry = 100*Math.random();
			//when 
			double actual = MathDeca.cos(entry);
			
			//then 
			double expected = Math.cos(entry);
			assertEquals(expected,actual,1E-13);
		}
	}
}
