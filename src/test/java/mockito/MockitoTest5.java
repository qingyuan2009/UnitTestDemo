package mockito;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest5 {
	
	@Test
	public void createMockObject() {
	    // 使用 mock 静态方法创建 Mock 对象.
	    List mockedList = mock(List.class);
	    Assert.assertTrue(mockedList instanceof List);

	    // mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
	    ArrayList mockedArrayList = mock(ArrayList.class);
	    Assert.assertTrue(mockedArrayList instanceof List);
	    Assert.assertTrue(mockedArrayList instanceof ArrayList);
	}
	
	@Test
	public void configMockObject() {
	    List mockedList = mock(List.class);
	    // 我们定制了当调用 mockedList.add("one") 时, 返回 true
	    when(mockedList.add("one")).thenReturn(true);
	    // 当调用 mockedList.size() 时, 返回 1
	    when(mockedList.size()).thenReturn(1);
	    Assert.assertTrue(mockedList.add("one"));
	    // 因为我们没有定制 add("two"), 因此返回默认值, 即 false.
	    Assert.assertFalse(mockedList.add("two"));
	    Assert.assertEquals(mockedList.size(), 1);
	    Iterator i = mock(Iterator.class);
	    when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
	    String result = i.next() + " " + i.next();
	    //assert
	    Assert.assertEquals("Hello, Mockito!", result);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testForIOException() throws Exception {
	    Iterator i = mock(Iterator.class);
	    when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
	    String result = i.next() + " " + i.next(); // 2
	    Assert.assertEquals("Hello, Mockito!", result);
	    //doThrow(new NoSuchElementException()).when(i).next(); // 3
	    when(i.next()).thenThrow(new NoSuchElementException());
	    
	    i.next(); // 4
	}

}
