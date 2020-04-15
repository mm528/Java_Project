package rmi.api;
import java.rmi.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import rmi.api.Attendance;
import rmi.api.Event;
import rmi.api.Invite;
import rmi.api.Notific;
import rmi.api.Reminders;
import rmi.api.employee;


/**
 * This is the interface that with be available on both the client and server side of the implementation.
 * All interaction will be done via this abstraction, allowing the client to call the methods from this
 * interface regardless of how they are implemented on the server side.
 * 
 * Any functionality of the server that is to be publicly available to the client must be implemented here.
 * @author Aaron, using http://littletutorials.com/2008/07/14/the-10-minutes-getting-started-with-rmi-tutorial/
 */
public interface Api extends Remote {
// 	public Data incrementCounter(Data value) throws RemoteException; // for testing only
	/** @see rmi.server.ApiImpl */
	public int login(String userName, String password) throws RemoteException;
//	public void createAccount() throws RemoteException; // not needed in java implementation.
	/** @see rmi.server.ApiImpl */
	public void undoInvite(int userId, int eventId) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void inviteUser(int userId, int eventId) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void cancelEvent( int eventId, int userId ) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void createEvent(int userId ,String eventName,String eventDescription,String eventDate,String startTime,String endTime,String location) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void updateEvent(int userId ,int eventId,String eventName,String eventDescription,String eventDate,String startTime,String endTime,String location) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void setReminder(int userId, int eventId, byte reminder) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void deleteReminder(int userId, int eventId, byte reminder) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public void acceptDecline(int userIdInt, int eventId, boolean attending) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public ArrayList<Notific> getNotifications(int currentUserID) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public ArrayList<Invite> getInvite(int userId) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public ArrayList<Reminders> getReminders(int userId) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public ArrayList<Event> getEvent(int userId) throws RemoteException;
	/** @see rmi.server.ApiImpl */
	public ArrayList<employee> getUserList() throws RemoteException;
}
