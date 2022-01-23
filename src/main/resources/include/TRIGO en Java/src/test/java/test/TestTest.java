package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestTest {
	
	@Test
	public void test() {
		//given 
		int a = 2;
		//when 
		int actual = a*a;
		//then
		int expected = 4;
		assertEquals(actual,expected);
	}
}
