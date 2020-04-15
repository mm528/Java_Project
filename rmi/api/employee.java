package rmi.api;

import java.io.Serializable;

/**
 * A data type for Employees, originally part of the database but moved to the rmi.api package
 * as it has to be distributed on both the client and server sides of the application.
 */
public class employee implements Serializable{
	private static final long serialVersionUID = -6126213296464120122L;
	public int     userID 		= 0;
	public int		phone		= 0;
	public int		officeNum	= 0;
	public int phoneNum			= 0;
	public String	userName	= "";
	public String  forename	= "";
	public String	lastName	= "";
	public String  password	= "";
	public String	email		= "";
	public boolean isSuperUser = false;
}
