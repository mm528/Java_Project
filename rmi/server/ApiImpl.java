package rmi.server;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import rmi.api.*;
/**
 * This is the server-side implementation of the rmi.
 * 
 * When a client invocates a method via rmi, the relevant method within this class will be executed on the server,
 * and the return value, if there is one, will be returned via rmi to the client.
 * 
 * For this project, this class only needs to call the Model counterparts of the methods specified below.
 * @author Aaron, using http://littletutorials.com/2008/07/14/the-10-minutes-getting-started-with-rmi-tutorial/
 */
public class ApiImpl extends UnicastRemoteObject implements Api {
    private static final long serialVersionUID = 1L;
    private int counter = 0;
    private Database.Model m;

    /**
     * Tries to initialise itself and an instance of the Database.Model class.
     * @throws RemoteException	
     */
    public ApiImpl() throws RemoteException {
        super();
		m = new Database.Model();
    }

/*    @Override // An old test method
    public synchronized Data incrementCounter(Data value) throws RemoteException {
        counter += value.getValue();
        return new Data(counter);
    }
*/

    
    @Override
    /**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
    public void acceptDecline(int userIdInt, int eventId, boolean attending1) throws RemoteException{
    	m.acceptDecline(userIdInt, eventId, attending1);
    }
    
    /**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
    @Override
	public int login(String userName, String password) throws RemoteException {
		return m.login(userName, password);
	}

    /**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void cancelEvent(int eventId, int userId) throws RemoteException {
		m.cancelEvent(eventId, userId);
		
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void createEvent(int userID, String eventName,
			String eventDescription, String eventDate, String startTime,
			String endTime, String location)
			throws RemoteException {
		m.createEvent(userID, eventName, eventDescription, eventDate, startTime, endTime, location, false);	
	}
	
	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void updateEvent(int userID, int eventID, String eventName,
			String eventDescription, String eventDate, String startTime,
			String endTime, String location)
			throws RemoteException {
		m.updateEvent(userID, eventID, eventName, eventDescription, eventDate, startTime, endTime, location);	
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public ArrayList<Notific> getNotifications(int currentUserID) throws RemoteException {
		return m.getNotifications(currentUserID);
	}

/*	@Override
	public void createAccount() throws RemoteException {
		m.createAccount();		
	}
*/
	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public ArrayList<Event> getEvent(int userId) throws RemoteException {
		return m.getEvent(userId);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public ArrayList<employee> getUserList() throws RemoteException {
		return m.getUserList();
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void undoInvite(int userId, int eventId) throws RemoteException {
		m.undoInvite(userId, eventId);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void inviteUser(int userId, int eventId) throws RemoteException {
		m.inviteUser(userId, eventId);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void setReminder(int userId, int eventId, byte reminder) throws RemoteException{
		m.setReminder(userId, eventId, reminder);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public void deleteReminder(int userId, int eventId, byte reminder) throws RemoteException{
		m.deleteReminder(userId, eventId, reminder);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public ArrayList<Invite> getInvite(int userId) throws RemoteException{
		return m.getInvite(userId);
	}

	/**
	 * Calls relevant method from Database.Model.
	 * @see Database.Model
	 */
	@Override
	public ArrayList<Reminders> getReminders(int userId) throws RemoteException{
		return m.getReminder(userId);
	}
}
