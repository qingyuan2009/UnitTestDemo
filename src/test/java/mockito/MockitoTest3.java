package mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// π”√built-in runner£∫MockitoJUnitRunner
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest3 {
	
	@Mock
    private List mockList;

    @Test
    public void shorthand(){
        mockList.add(1);
        verify(mockList).add(1);
    }

}
