package rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import rmi.api.*;
/**
 * This is the client-side implementation, this will request information from the server and pass parameters via
 * the rmi using the api interface to the server. This will be used vy the GUI applications to connect to the server.
 * @author Aaron, using http://littletutorials.com/2008/07/14/the-10-minutes-getting-started-with-rmi-tutorial/
 */
public class Client{
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    private static Api remoteApi;

    /*
     * @deprecated Used for testing purposes only
     * @param args	- not used.
     * @throws Exception
     */
    /*public static void main(String[] args) throws Exception {
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
    /*    for (int i = 1; i <= 100; i++) {
            System.out.println("counter = " +
                remoteApi.incrementCounter(new Data(1)).getValue());
            Thread.sleep(100);
        } *//*
        System.out.println(remoteApi.login("Guyqw", "fwefwef"));
        remoteApi.createEvent(1, "tada", "a description","2015-12-31","16:00:00","22:00:00", "moon");
    }*/
    
    
    /**
     * Create a new client, will automatically try to locate the server via rmi.
     */
    public Client(){
    	try{
    		registry = LocateRegistry.getRegistry(HOST, PORT);
            remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
    	}
    	catch (Exception e){
    		System.out.println("Client connection exception: " + e.toString());
    	}
    }
    
    /**
     * Used to either accept or decline an invitation to an event.
     * @param userIdInt		The user who is accepting or declining the invitation.
     * @param eventId		The event of the invitation they are responding to.
     * @param attending1	Whether or not the invitation is being accepted.
     */
    public void acceptDecline(int userIdInt, int eventId, boolean attending1){
    	try {
			remoteApi.acceptDecline(userIdInt, eventId, attending1);
		} catch (RemoteException e) {
			System.out.println("Client acceptDecine exception: " + e.toString());
		}
    }
    
    /**
     * Supply a userName and password and this function shall return the user's ID if the login was successful,
     * -1 if it was not.
     * @param userName	
     * @param password	
     * @return the user's ID if the login was successful, -1 if it was not.
     */
    public int login(String userName, String password){
    	try {
			return remoteApi.login(userName, password);
		} catch (RemoteException e) {
			System.out.println("Client login exception: " + e.toString());
			return -1;
		}
    }
	
    /**
     * Will cancel the event with the givenID, if the given userID is the same as the one who created the event.
     * @param eventId	
     * @param userId	
     */
	public void cancelEvent(int eventId, int userId){
		try {
			remoteApi.cancelEvent(eventId, userId);
		} catch (RemoteException e) {
			System.out.println("Client cancelEvent exception: " + e.toString());
		}
	}

	/**
     * Will create a new event with the supplied information.
     * @param userID	The user who is creating the event.
     * @param eventName	The display name for the event, <=150 characters.
     * @param eventDescription	A description of the event, essentially unlimited space.
     * @param eventDate	The date of the event.
     * @param startTime The start time of the event.
     * @param endTime	The end time of the event.
     * @param location	The location of the event, <=10 characters.
     * @param allDayEvent	Deprecated, but kept to ensure compatibility.
     */
	public void createEvent(int userID, String eventName,
			String eventDescription, String eventDate, String startTime,
			String endTime, String location, boolean allDayEvent){
		try {
			remoteApi.createEvent(userID, eventName, eventDescription, eventDate, startTime, endTime, location);
		} catch (RemoteException e) {
			System.out.println("Client createEvent exception: " + e.toString());
		}
	}

	/**
     * Will update the given event with the supplied information, if the userID provided is the same as the one who
     * created the event to begin with.
     * @param userID	The user who is trying to update the event, will be used to validate their authority to do so.
	 * @param eventID	The event to be updated.
     * @param eventName	The display name for the event, <=150 characters.
     * @param eventDescription	A description of the event, essentially unlimited space.
     * @param eventDate	The date of the event.
     * @param startTime The start time of the event.
     * @param endTime	The end time of the event.
     * @param location	The location of the event, <=10 characters.
     * @param allDayEvent	Deprecated, but kept to ensure compatibility.
     */
	public void updateEvent(int userID, int eventID, String eventName,
			String eventDescription, String eventDate, String startTime,
			String endTime, String location, boolean allDayEvent){
		try {
			remoteApi.updateEvent(userID, eventID, eventName, eventDescription, eventDate, startTime, endTime, location);
		} catch (RemoteException e) {
			System.out.println("Client createEvent exception: " + e.toString());
		}
	}
	
	
	/**
     * Will get all the notifications for a user's ID.
     * @param currentUserID		The user who's notifications should be collected.
     * @return	Will return the notifications for the given user, Null if connection failed.
     */
	public ArrayList<Notific> getNotifications(int currentUserID){
		try {
			return remoteApi.getNotifications(currentUserID);
		} catch (RemoteException e) {
			System.out.println("Client getNotifications exception: " + e.toString());
			return null;
		}
	}
/*
	public void createAccount() throws RemoteException {
		try {
			remoteApi.createAccount();
		} catch (RemoteException e) {
			System.out.println("Client createAccount exception: " + e.toString());
		}
	}
*/
	/**
	 * Will get all of the events for a user's ID.
	 * @param userId	The user who's events should be collected.
	 * @return Will return an ArrayList filled with the events (of the rmi.api.Event type class) for the given user.
	 */
	public ArrayList<Event> getEvent(int userId){
		try {
			return remoteApi.getEvent(userId);
		} catch (RemoteException e) {
			System.out.println("Client getEvent exception: " + e.toString());
			return null;
		}
	}

	/**
	 * Get all users from database.
	 * @return Will return all of the employees in the database, Null if connection failed.
	 */
	public ArrayList<employee> getUserList(){
		try {
			return remoteApi.getUserList();
		} catch (RemoteException e) {
			System.out.println("Client getUserList exception: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Used to retract an invitation to an event.
	 * @param userId	User who's invitation should be retracted.
	 * @param eventId	Event for which the invitation is being rejected.
	 */
	public void undoInvite(int userId, int eventId) {
		try {
			remoteApi.undoInvite(userId, eventId);
		} catch (RemoteException e) {
			System.out.println("Client undoInvite exception: " + e.toString());
		}
	}
	
	/**
	 * Used to invite a user to an event.
	 * @param userId	User who should be invited.
	 * @param eventId	Event the user should be invited to.
	 */
	public void inviteUser(int userId, int eventId) {
		try {
			remoteApi.inviteUser(userId, eventId);
		} catch (RemoteException e) {
			System.out.println("Client inviteUser exception: " + e.toString());
		}
	}
	
	/**
	 * Set a reminder for a user.
	 * @param userId	The user to set the reminder for.
	 * @param eventId	The event to set the reminder for.
	 * @param reminder	The amount of hours before the event a user wishes to be reminded, <=12.
	 */
	public void setReminder(int userId, int eventId, byte reminder){
		try {
			remoteApi.setReminder(userId, eventId, reminder);
		} catch (RemoteException e) {
			System.out.println("Client setReminder exception: " + e.toString());
		}
	}
	
	/**
	 * Delete a reminder for a user. The reminder byte must still be present as the user can have multiple
	 * reminders for a single event, the byte itself is the only way to differentiate.
	 * @param userId	The user to delete the reminder for.
	 * @param eventId	The event to delete the reminder for.
	 * @param reminder	The amount of hours before the event a user wishes to be reminded, <=12.
	 */
	public void deleteReminder(int userId, int eventId, byte reminder) {
		try {
			remoteApi.deleteReminder(userId, eventId, reminder);
		} catch (RemoteException e) {
			System.out.println("Client deleteReminder exception: " + e.toString());
		}
	}
	
	/**
	 * Will get all of the invitations for a user's ID.
	 * @param userId	The user who's invitations should be collected.
	 * @return Will return the invitations for the given user, Null if connection failed.
	 */
	public ArrayList<Invite> getInvite(int userId) {
		try {
			return remoteApi.getInvite(userId);
		} catch (RemoteException e) {
			System.out.println("Client getInvite exception: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Will get all of the reminders for a user's ID.
	 * @param userId	The user who's reminders should be collected.
	 * @return Will return the reminders for the given user, Null if connection failed.
	 */
	public ArrayList<Reminders> getReminders(int userId) {
		try {
			return remoteApi.getReminders(userId);
		} catch (RemoteException e) {
			System.out.println("Client getReminders exception: " + e.toString());
			return null;
		}
	}
    
        
}