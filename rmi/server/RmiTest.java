package rmi.server;

import static org.junit.Assert.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.Ignore;

import rmi.client.Client;

/**
 * A Junit Test class for the RMI, it assumes you don't have the Server.java already running
 * when it is executed. If you do have it running the tests will fail. The tests are 
 * independent of the MySql server, although they should pass/fail appropriately
 * regardless of the state of the MySql server.
 * @author Aaron
 */
public class RmiTest{

	@Ignore("Will not work at the same time as testConnection, hence ignored.")
	public void initialiseServer() {
		Server s = null;
		try {
			s = new Server();
		} catch (Exception e) {
			fail(e.toString());
			System.out.println("Make sure you aren't already running a server/serverTest");
		}
		assertNotNull("Server is null!",s);
	}
	
	@Ignore("No useful test could be made of this without the database running")
	public void initialiseClient(){
		Client c = null;
		c = new Client();
		assertNotNull("Client is null!",c);
	}
	
	/**
	 * Will test whether the server and client can be initialised and whether or not they can
	 * communicate via rmi, should work independently of the database server.
	 */
	@Test
	public void testConnection(){
		try {
			Server s = new Server();
		} 
		catch (RemoteException e) {
		} 
		catch (AlreadyBoundException e) {
			fail("port already bound, server may already be open");
		}
		Client c = new Client();
		try{
		c.login("probablyNot" , "aRealUser");
		}
		catch (NullPointerException e){ // Either this exception, or no exception, should be triggered by this test.
		}
		catch (Exception e){
			fail("Should be null pointer exception as data unavailable, or no exception as db connected \n" + e.toString());
		}
	}
	

}
