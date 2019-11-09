package mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.*;
// import static �����û�ֱ��ʹ����ľ�̬������������������
// mock(List.class) ����ֱ��ʹ�� Mockito* ��� public static <T> T mock(Class<T> classToMock)
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockitoTest {
	
	@Test
	//��֤��Ϊ
    public void verify_behaviour(){
        //ģ�ⴴ��һ��List����
        List mock = mock(List.class);
        //ʹ��mock�Ķ���
        mock.add(1);
        mock.clear();
        //��֤add(1)��clear()��Ϊ�Ƿ���
        verify(mock).add(1);
        verify(mock).clear();
    }
	
    @Test
    //ģ�������������Ľ��
    public void when_thenReturn(){
        //mockһ��Iterator��
        Iterator iterator = mock(Iterator.class);
        //Ԥ�赱iterator����next()ʱ��һ�η���hello����n�ζ�����world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //ʹ��mock�Ķ���
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //��֤���
        assertEquals("hello world world",result);
    }
    
    @Test(expected = IOException.class)
    //ģ�������������Ľ��
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //Ԥ�赱���ر�ʱ�׳��쳣
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }
    
    @Test
    /*
     * RETURNS_SMART_NULLSʵ����Answer�ӿڵĶ������Ǵ���mock����ʱ��һ����ѡ������     * mock(Class,Answer)��
         *  �ڴ���mock����ʱ���еķ�������û�н���stubbing�����Ե���ʱ��Ż�Null�����ڽ��в����Ǻܿ����׳�NullPointerException��
         *  ���ͨ��RETURNS_SMART_NULLS����������mock������û�е���stubbed����ʱ�᷵��SmartNull��
         *  ���磺����������String���᷵��"";��int���᷵��0����List���᷵�ؿյ�List�����⣬�ڿ���̨�����п��Կ���SmartNull���Ѻ���ʾ��
     */
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));
        
        //ʹ��RETURNS_SMART_NULLS����������mock���󣬲����׳�NullPointerException�쳣���������̨���ڻ���ʾ��Ϣ��SmartNull returned by unstubbed get() method on mock��
        System.out.println(mock.toArray().length);
    }
    
    //ģ�ⷽ�����׳��쳣
    @Test(expected = RuntimeException.class)
    public void doThrow_when(){
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }
    
    //����ƥ��
    @Test
    public void with_arguments(){
        Comparable comparable = mock(Comparable.class);
        //Ԥ����ݲ�ͬ�Ĳ������ز�ͬ�Ľ��
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        //����û��Ԥ�������᷵��Ĭ��ֵ
        assertEquals(0, comparable.compareTo("Not stub"));
    }
    
    //����ƥ���ƶ������⣬������ƥ���Լ���Ҫ���������
    @Test
    public void with_unspecified_arguments(){
        List list = mock(List.class);
        //ƥ���������
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(!list.contains(3));
    }

    private class IsValid extends ArgumentMatcher<List>{
        @Override
        public boolean matches(Object o) {
            return (Integer)o == 1 || (Integer)o == 2;
        }
    }
    
    //�����ʹ���˲���ƥ�䣬��ô���еĲ���������ͨ��matchers��ƥ��
    @Test
    public void all_arguments_provided_by_matchers(){
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao","hello");
        //�����ʹ���˲���ƥ�䣬��ô���еĲ���������ͨ��matchers��ƥ��
        verify(comparator).compare(anyString(),eq("hello"));
        //�����Ϊ��Ч�Ĳ���ƥ��ʹ��
        //verify(comparator).compare(anyString(),"hello");
    }
    
    @Test
    public void argumentMatchersTest(){
        //����mock����
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)��������Ӧ���Զ���Ĺ��򣬿��Դ����κ�ʵ��Matcher�ӿڵ�ʵ���ࡣ
        when(mock.addAll(argThat(new IsListofTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one","two","three"));
        //IsListofTwoElements����ƥ��sizeΪ2��List����Ϊ���Ӵ���ListΪ����Ԫ�أ����Դ�ʱ��ʧ�ܡ�
        verify(mock).addAll(argThat(new IsListofTwoElements()));
    }
    
    class IsListofTwoElements extends ArgumentMatcher<List>
    {
        public boolean matches(Object list)
        {
            return((List)list).size()==3;
        }
    }

}
