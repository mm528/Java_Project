package rmi.api;

import java.io.Serializable;

/**
 * A data type for Attendance to an event, originally part of the database but moved to the rmi.api 
 * package as it has to be distributed on both the client and server sides of the application.
 */
public class Attendance implements Serializable{
	private static final long serialVersionUID = -924336757680466483L;
	public int eventId 		= 0;
	public int userId  		= 0;
	public boolean attending 	= true;
}
