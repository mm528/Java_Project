package rmi.api;

import java.io.Serializable;

/**
 * Created by Arisato on 09/05/15.
 * Made Serializable by Aaron on 14/05/15.
 * A data type for Notifications, originally part of the database but moved to the rmi.api package
 * as it has to be distributed on both the client and server sides of the application.
 */
public class Notific implements Serializable{
	private static final long serialVersionUID = -1656754659199280744L;
	public int eventId;
    public int userId;
}
