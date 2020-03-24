package jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import jUnit.MessageUtil;

public class jUnitTest02 {

	String message = "Hello World";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		assertEquals(message, messageUtil.printMessage());
	}

	@Test
	public void testAdd() {
		// test data
		int num = 5;
		String temp = null;
		String str = "Junit is working fine";

		// check for equality
		assertEquals("Junit is working fine", str);

		// check for false condition
		assertFalse(num > 6);

		// check for not null value
		assertNotNull(str);
	}

	@Test(expected = ArithmeticException.class)
	public void testPrintErrorMessage() {
		System.out.println("Inside testPrintMessage()");
		messageUtil.printErrorMessage();
	}

}
