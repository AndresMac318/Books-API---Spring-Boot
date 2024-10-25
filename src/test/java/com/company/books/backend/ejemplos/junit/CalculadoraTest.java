package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calc;
	
	// LIFECICLE: se ejecuta 1 sola vez y al principio del test
	@BeforeAll
	public static void first() {
		System.out.println("primero");
	}
	
	// se ejecuta 1 sola vez y al final del test
	@AfterAll
	public static void end() {
		System.out.println("ultimo");
	}
	
	// se ejecuta cada vez antes de comenzar 1 test unitario
	@BeforeEach
	public void instanceObject() {
		calc = new Calculadora();
		System.out.println("@BeforeEach");
	}
	
	// se ejecuta cada vez que termine  1 test unitario
	@AfterEach
	public void afterTest() {
		System.out.println("@AfterEach");
	}
	
	@Test
	@DisplayName("Prueba que ocupa asserEquals")
	@Disabled("Esta prueba no se ejecutara")
	public void calculadoraAssertEqualTest() {
		
		assertEquals(2, calc.sumar(1, 1));
		assertEquals(3, calc.restar(4, 1));
		assertEquals(9, calc.multiplicar(3, 3));
		assertEquals(2, calc.dividir(4, 2));
		
		System.out.println("calculadoraAssertEqualTest");
	}
	
	@Test
	public void calculadoraTrueFalseTest() {
		
		assertTrue(calc.sumar(1, 1) == 2 );
		assertTrue(calc.restar(4, 1) == 3 );
		assertFalse(calc.multiplicar(3, 3) == 8 );
		assertFalse(calc.dividir(4, 2) == 1);
		
		System.out.println("calculadoraTrueFalseTest");
	}

}
