package mockito;

import static org.mockito.Mockito.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class MockitoDeepStubs {
	
	/*
	 * RETURNS_DEEP_STUBS也是创建mock对象时的备选参数
	 * RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，方法deepstubsTest和deepstubsTest2是等价的
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
