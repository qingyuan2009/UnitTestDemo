package mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockitoTest2 {

	@Mock
	//使用注解来快速模拟
    private List mockList;
	
	//mock的对象为NULL，为此我们必须在基类中添加初始化mock的代码
	public MockitoTest2(){
        MockitoAnnotations.initMocks(this);
    }
	
	 @Test
	 public void shorthand(){
	    mockList.add(1);
	    verify(mockList).add(1);
	 }
}
