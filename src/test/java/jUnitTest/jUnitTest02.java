package jUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jUnit.MessageUtil;

public class jUnitTest02 {

	String message = "Hello World";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		assertEquals(message, messageUtil.printMessage());
	}

}
