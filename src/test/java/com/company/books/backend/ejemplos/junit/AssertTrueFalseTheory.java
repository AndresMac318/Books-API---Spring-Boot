package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalseTheory {
	
	@Test
	public void test1() {
		assertTrue(true);
		assertFalse(false);
	}
	
	@Test
	public void test2() {
		
		boolean expression = 4 == 4; 
		boolean expression2 = 4 == 3; 
		
		assertTrue(expression);
		assertFalse(expression2);
	}

}
