package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTheory {
	
	@Test
	public void mytest2() {
		
		assertNotEquals(1, 2);
		// fail: assertNotEquals(1, 1);		
	}

}
