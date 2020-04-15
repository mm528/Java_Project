package rmi.api;

import java.io.Serializable;

/**
 * Created by Arisato on 12/05/15.
 * Made Serializable by Aaron on 14/05/15.
 * A data type for Invitations, originally part of the database but moved to the rmi.api package
 * as it has to be distributed on both the client and server sides of the application.
 */
public class Invite implements Serializable{
	private static final long serialVersionUID = 7996805545899235531L;
	public short userID1;
    public int eventID1;
    public boolean attending1;
}
