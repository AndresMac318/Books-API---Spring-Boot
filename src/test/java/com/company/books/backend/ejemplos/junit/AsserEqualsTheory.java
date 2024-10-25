package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AsserEqualsTheory {

	@Test
	public void myTest() {
		
		assertEquals(1, 1);
		// fail: assertEquals(1, 2);
	}
	
}
