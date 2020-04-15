package junit;

import static org.junit.Assert.*;
import rmi.client.Client;
import org.junit.Test;
import view.GlobalVariables;
public class LoginFrameTest {
	Client c;
	@Test
	public void testfalseData() {

	for (int i = 0; i<50;i++){
		String user = Integer.toString(i);
		String password = Integer.toString(i);
		
		GlobalVariables.userID = c.login(user, password);
	
	assertFalse(GlobalVariables.userID !=-1);
	}
	
	}

	@Test
	public void testtrue() {
		String user = "User1";
		String password = "1234";
		
		GlobalVariables.userID = c.login(user, password);
	
	assertTrue(GlobalVariables.userID !=-1);
	}

}
