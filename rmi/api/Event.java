package rmi.api;
import java.sql.Date;
import java.sql.Time;
import java.io.Serializable;

/**
 * A data type for Events, originally part of the database but moved to the rmi.api package
 * as it has to be distributed on both the client and server sides of the application.
 */
public class Event implements Serializable{
	private static final long serialVersionUID = -644127996367256284L;
	public int eventId	 	= 0;	// Event id
	public int  userId   	= 0;	// User who created
	public String eventName = "";	// Name given for event
	public String eventDesc = "";	// Event Description
	public String date 	 	= null;	// Date of event
	public String startTime 	= null;	// Time of event
	public String endTime 	= null;	// Time of event
	public String location 	= ""; 	// Room number
	public boolean allDayEvent	    = false;
	public int monthDate;
	public int dayDate;

}
