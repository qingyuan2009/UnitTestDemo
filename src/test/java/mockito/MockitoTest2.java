package mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockitoTest2 {

	@Mock
	//ʹ��ע��������ģ��
    private List mockList;
	
	//mock�Ķ���ΪNULL��Ϊ�����Ǳ����ڻ�������ӳ�ʼ��mock�Ĵ���
	public MockitoTest2(){
        MockitoAnnotations.initMocks(this);
    }
	
	 @Test
	 public void shorthand(){
	    mockList.add(1);
	    verify(mockList).add(1);
	 }
}
