package mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import production.ClassA;
import production.ClassPartial;
import production.ClassStatic;

@RunWith(PowerMockRunner.class)
//@PrepareForTest(ClassA.class)
//fullyQualifiedNames=  没有空格
@PrepareForTest(fullyQualifiedNames = "production.*")
public class TestClassA {

	@Test
	public void test_not_mocked() throws Throwable {
		assertThat(new ClassA("random string").check(), equalTo("checked random string"));
	}

	@Test
	public void test_ClassA() throws Throwable {
		// Mock object
		ClassA a = mock(ClassA.class);

		// set an expectation telling that whenever the constructor of that class is
		// invoked,
		// a mock instance should be returned rather than a real one
		PowerMockito.whenNew(ClassA.class).withArguments(Mockito.anyString()).thenReturn(a);

		// construction mocking works in action, then verify the behaviors of PowerMock
		ClassA a1 = new ClassA("abc");
		PowerMockito.verifyNew(ClassA.class).withArguments(Mockito.anyString());
		// PowerMockito.verifyNew(ClassA.class).withArguments("bbc"); // will fail

		// test method in mock object, so we test class
		when(a.check()).thenReturn("test");
		assertThat(new ClassA("random string").check(), equalTo("test"));

		// test method in object instance
		when(a1.check()).thenReturn("test");
		assertThat(a1.check(), equalTo("test"));
	}

	@Test
	public void test_ClassStatic() throws Throwable {

		// Test class static method
		PowerMockito.mockStatic(ClassStatic.class);
		when(ClassStatic.firstMethod(Mockito.anyString())).thenReturn("Hello Baeldung!");
		when(ClassStatic.secondMethod()).thenReturn("Nothing special");
		
		// Mock 方法
		String firstWelcome = ClassStatic.firstMethod("Whoever");
		String secondWelcome = ClassStatic.firstMethod("Whatever");
		assertEquals("Hello Baeldung!", firstWelcome);
		assertEquals("Hello Baeldung!", secondWelcome);
		
		// firstMethod 是否调用了两次
		PowerMockito.verifyStatic(Mockito.times(2));
		ClassStatic.firstMethod(Mockito.anyString());
		
		// secondMethod 是否调用过
		PowerMockito.verifyStatic(Mockito.never());
		ClassStatic.secondMethod();		
		
	}
	
	@Test(expected = RuntimeException.class)
	public void givenStaticMethods_whenUsingPowerMockito_thenCorrect() {
		PowerMockito.mockStatic(ClassStatic.class);
		// 人为抛异常
		PowerMockito.doThrow(new RuntimeException()).when(ClassStatic.thirdMethod());
		ClassStatic.thirdMethod();  
	}
	
	@Test
	public void testClassPartial() throws Exception {
		PowerMockito.spy(ClassPartial.class);
		// verify static method
		when(ClassPartial.staticMethod()).thenReturn("I am a static mock method.");
		String returnValue = ClassPartial.staticMethod();
		PowerMockito.verifyStatic();
		ClassPartial.staticMethod();
		assertEquals("I am a static mock method.", returnValue);
		
		// verify final method
		ClassPartial classPartial = new ClassPartial();
		ClassPartial mock = PowerMockito.spy(classPartial);
		when(mock.finalMethod()).thenReturn("I am a final mock method.");
		returnValue = mock.finalMethod();
		Mockito.verify(mock).finalMethod();
		assertEquals("I am a final mock method.", returnValue);
		
		// verify private method
		PowerMockito.when(mock, "privateMethod").thenReturn("I am a private mock method.");
		returnValue = mock.privateMethodCaller();
		PowerMockito.verifyPrivate(mock).invoke("privateMethod");
		assertEquals("I am a private mock method. Welcome to the Java world.", returnValue);
	}
	
	

}
