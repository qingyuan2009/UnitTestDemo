package jUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class jUnitTest01 {

	@Test
	public void testAdd() {
		String str = "Junit is working fine";
		assertEquals("Junit is working fine", str);
	}

}
