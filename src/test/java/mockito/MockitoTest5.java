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
	    // ʹ�� mock ��̬�������� Mock ����.
	    List mockedList = mock(List.class);
	    Assert.assertTrue(mockedList instanceof List);

	    // mock ������������ Mock �ӿ���, ������ Mock ���������.
	    ArrayList mockedArrayList = mock(ArrayList.class);
	    Assert.assertTrue(mockedArrayList instanceof List);
	    Assert.assertTrue(mockedArrayList instanceof ArrayList);
	}
	
	@Test
	public void configMockObject() {
	    List mockedList = mock(List.class);
	    // ���Ƕ����˵����� mockedList.add("one") ʱ, ���� true
	    when(mockedList.add("one")).thenReturn(true);
	    // ������ mockedList.size() ʱ, ���� 1
	    when(mockedList.size()).thenReturn(1);
	    Assert.assertTrue(mockedList.add("one"));
	    // ��Ϊ����û�ж��� add("two"), ��˷���Ĭ��ֵ, �� false.
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
