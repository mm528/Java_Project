package view;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import rmi.api.Event;
import rmi.api.Notific;
import rmi.api.employee;
import rmi.client.Client;
import view.GlobalVariables;
public class DailyController {

 private  Client c;
	
 public DailyController(){

		c = new Client();
 }
 public  ArrayList<Event> getEvent(int userid) {
	

return c.getEvent(userid);
}


public void createEvent(int userID, String eventName,String eventDescription,
		String eventDate, String startTime,String endTime, String location, boolean allDayEvent){
	
	c.createEvent(userID,eventName,eventDescription,
			eventDate, startTime,endTime,location,allDayEvent);
}

public void cancelEvent(int eventId, int userId){
	c.cancelEvent(eventId,userId);
}



public ArrayList<employee> getUserList(){
	return c.getUserList();
	
}

public ArrayList<Notific> getNotifications(int userID){
	return c.getNotifications(userID);
	
}

public void undoInvite(int userId, int eventId){
	c.undoInvite(userId, eventId);
}

public void inviteUser(int userId, int eventId){
	c.inviteUser(userId, eventId);
}
}
////////