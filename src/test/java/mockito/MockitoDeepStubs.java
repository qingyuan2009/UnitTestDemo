package mockito;

import static org.mockito.Mockito.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class MockitoDeepStubs {
	
	/*
	 * RETURNS_DEEP_STUBSҲ�Ǵ���mock����ʱ�ı�ѡ����
	 * RETURNS_DEEP_STUBS����������Զ�����mock����Ķ��󣬷���deepstubsTest��deepstubsTest2�ǵȼ۵�
	 * 
	 */
	
	@Test
    public void deepstubsTest(){
        Account account=mock(Account.class,RETURNS_DEEP_STUBS);
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }
    @Test
    public void deepstubsTest2(){
        Account account=mock(Account.class); 
        RailwayTicket railwayTicket=mock(RailwayTicket.class);        
        when(account.getRailwayTicket()).thenReturn(railwayTicket); 
        when(railwayTicket.getDestination()).thenReturn("Beijing");
        
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();    
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }    
    
    public class RailwayTicket{
        private String destination;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }        
    }
    
    public class Account{
        private RailwayTicket railwayTicket;

        public RailwayTicket getRailwayTicket() {
            return railwayTicket;
        }

        public void setRailwayTicket(RailwayTicket railwayTicket) {
            this.railwayTicket = railwayTicket;
        }
    }

}
