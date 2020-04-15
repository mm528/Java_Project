package Database;

import java.lang.String;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import rmi.api.Attendance;
import rmi.api.employee;
import rmi.api.Event;
import rmi.api.Invite;
import rmi.api.Notific;
import rmi.api.Reminders;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * this is the project
 * @author Darren ward, Mindugas Rinkunas of the connection was taken from
 * http://www.ccs.neu.edu/home/kathleen/classes/cs3200/JDBCtutorial.pdf
 */
public class Model {
	
	private final String userName = "dbadmin";
	private final String password = "CrEate-User";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "test";

//	private final String userName 	= "root";
//	private final String password 	= "1234";
//	private final String serverName = "127.0.0.1";
//	private final int portNumber 	= 3306;
//	private final String dbName 	= "sys";

//	private final String userName 	= "root";
//	private final String password 	= "1234";
//	private final String serverName = "127.0.0.1";
//	private final int portNumber 	= 3306;
//	private final String dbName 	= "test";

	public Model(){
		try {
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Get a new database connection
	 * @return A new connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		java.sql.Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/" + this.dbName, connectionProps);
		return (Connection) conn;
	}
	/**
	 * Run a SQL command which does not return a record set:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * @author dw220
	 * @throws SQLException
	 *             If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {
			// This will run whether we throw an exception or not
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	//For testing purpose ONLY. Destroy this method before compiling the APP
	public void deleteUser(String userNam){
		Connection conn;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("DELETE FROM User WHERE userName = " + "'"+ userNam +"'");
			st.executeUpdate(sql);
			System.out.println("User deleted");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete the user: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID and event ID to remove an invitation
	 * @param userIdInt	An int for the users ID
	 * @param eventId	An int for the event ID
	 */
	public void undoInvite(int userIdInt, int eventId){
		short userId = (short) userIdInt;
		Connection conn = null;
		CallableStatement stmt;
		String deleteInviteProc = "{call delete_invite(?,?)}";
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		try {
			stmt = conn.prepareCall(deleteInviteProc);

			stmt.setShort(1,userId);
			stmt.setInt(2,eventId);

			// execute delete_invite stored procedure
			stmt.executeUpdate();
			stmt.close();
			conn.close();

			System.out.println("Undo invite successful");

		} catch (SQLException e) {
			System.out.println("ERROR: Could not undo the invite: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID and event ID to invite particular user into particular event
	 * @param userIdInt	An int for the users ID
	 * @param eventId	An int for the event ID
	 */
	public void inviteUser(int userIdInt, int eventId){
		short userId = (short) userIdInt;
		Connection conn;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("INSERT INTO Attendance(userID,eventID) VALUES ("+userId+","+eventId+");");
			st.executeUpdate(sql);
			System.out.println("User invited");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not invite the user: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID and event ID to invite particular user into particular event
	 * @param userIdInt	An int for the users ID
	 * @param eventId	An int for the event ID
	 * @param attending1 A boolean to accept or decline an invitation
	 */
	public void acceptDecline(int userIdInt, int eventId, boolean attending1){
		short userId = (short) userIdInt;
		Connection conn;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("UPDATE Attendance SET attending = " +attending1+ " WHERE userID = "
							+userId+ " && eventID = " +eventId);
			st.executeUpdate(sql);
			System.out.println("Invitation processed");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not invite the user: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID to retrieve a list of invites for the user
	 * @param userIdInt	An int for the users ID
	 */
	public ArrayList<Invite> getInvite(int userIdInt){
		short userId = (short) userIdInt;
		ArrayList<Invite> invites = new ArrayList<Invite>();
		Connection conn = null;
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// get the invites list
		try {
			Statement st = (Statement) conn.createStatement();
			String sql = ("SELECT * FROM Attendance WHERE userID = " +userId+ " && attending <=> NULL");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Invite e 		= new Invite();
				e.userID1 		= rs.getShort("userID");
				e.eventID1		= rs.getInt("eventID");
				e.attending1	= rs.getBoolean("attending");
				invites.add(e); // add the results to array list to return
			}
		} catch (SQLException ex) {
			System.out.println("problem with result set: " +ex);
		}
		closeConn(conn);
		return invites;
	}

	/**
	 * Provide user ID, event ID and time of the reminder to set the reminder
	 * @param userIdInt	An int for the users ID
	 * @param eventId	An int for the event ID
	 * @param reminder  A byte for the reminder time
	 */
	public void setReminder(int userIdInt, int eventId, byte reminder){
		short userId = (short) userIdInt;
		Connection conn;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("INSERT INTO Reminder(userID,eventID,reminderTime) VALUES ("+userId+","+eventId+","+reminder+");");
			st.executeUpdate(sql);
			System.out.println("User reminder set");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not set the reminder: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID, event ID and time of the reminder to delete the reminder
	 * @param userIdInt	An int for the users ID
	 * @param eventId	An int for the event ID
	 * @param reminder  A byte for the reminder time
	 */
	public void deleteReminder(int userIdInt, int eventId, byte reminder){
		short userId = (short) userIdInt;
		Connection conn;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("DELETE FROM Reminder WHERE userID = " +userId+ " && eventID = "
					 +eventId+ " && reminderTime = " +reminder);
			st.executeUpdate(sql);
			System.out.println("Reminder deleted or does not exist.");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete the reminder: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID to retrieve the list of the reminders for the particular user
	 * @param userIdInt	An int for the users ID
	 */
	public ArrayList<Reminders> getReminder(int userIdInt){
		short userId = (short) userIdInt;
		ArrayList<Reminders> reminders = new ArrayList<Reminders>();
		Connection conn = null;
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// get the reminders list
		try {
			Statement st = (Statement) conn.createStatement();
			String sql = ("SELECT * FROM Reminder WHERE userID = " +userId);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Reminders e 	= new Reminders();
				e.userID1 		= rs.getShort("userID");
				e.eventID1		= rs.getInt("eventID");
				e.reminderTime1 = rs.getByte("reminderTime");
				reminders.add(e); // add the results to array list to return
			}
		} catch (SQLException ex) {
			System.out.println("problem with result set: " +ex);
		}
		closeConn(conn);
		return reminders;
	}


	/**
	 * Provide parameters listed below to create an event
	 * @param userIdInt		   An int for the users ID
	 * @param eventName 	   A String for the event name
	 * @param eventDescription A String for the event description
	 * @param eventDate 	   A String for the date of an event
	 * @param startTime 	   A String for the time on which event starts
	 * @param endTime 		   A String for the time on which event ends
	 * @param location		   A String for the Room of the event
	 * @param allDayEvent 	   A boolean to confirm if its an all day event
	 */
	public void createEvent(int userIdInt ,String eventName,String eventDescription,String eventDate,String startTime,String endTime,String location,boolean allDayEvent){
		short userId = (short) userIdInt;
		Connection conn = null;
		CallableStatement stmt;
		String createEventProc = "{call create_event(?,?,?,?,?,?,?,?)}";
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// Create Event
		try {
			stmt = conn.prepareCall(createEventProc);
			stmt.setShort(1,userId);
			stmt.setString(2, eventName);
			stmt.setString(3,eventDescription);
			stmt.setString(4, eventDate);
			stmt.setString(5,startTime);
			stmt.setString(6,endTime);
			stmt.setString(7,location);
			stmt.setBoolean(8,allDayEvent);
			// execute create_event stored procedure
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide parameters listed below to update an event
	 * @param userID1		    An int for the users ID
	 * @param eventID1		    An int for the event ID
	 * @param eventName1 	    A String for the event name
	 * @param eventDescription1 A String for the event description
	 * @param eventDate1 	    A String for the date of an event
	 * @param startTime1 	    A String for the time on which event starts
	 * @param endTime1 		    A String for the time on which event ends
	 * @param location1		    A String for the Room of the event
	 */
	public void updateEvent(int userID1, int eventID1, String eventName1, String eventDescription1, String eventDate1, String startTime1, String endTime1, String location1){
		Connection conn;

		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement st = (Statement) conn.createStatement();
			String sql =
					("UPDATE Event SET eventName = " + "'"+eventName1+"'" +", eventDescription = "
							+ "'" +eventDescription1+ "'" +", eventDate = "+ "'" +eventDate1+ "'"
							+", startTime = "+ "'" +startTime1+ "'" +", endTime = "+ "'" +endTime1+ "'"
							+", location = "+ "'" +location1+ "'" +" WHERE userID = " +userID1+ " && eventID = "
							+eventID1);
			st.executeUpdate(sql);
			System.out.println("Reminder deleted or does not exist.");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete the reminder: " +e);
			e.printStackTrace();
			return;
		}
		closeConn(conn);
	}

	/**
	 * Provide user ID to get a list of notifications for the particular user
	 * @param currentUserID An int for the users ID
	 */
	public ArrayList<Notific> getNotifications(int currentUserID){

		ArrayList<Attendance> attend = new ArrayList<Attendance>();
		ArrayList<Notific> notific = new ArrayList<Notific>();

		Connection conn = null;
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// get eventId list for the current user
		try {
			Statement st = (Statement) conn.createStatement();
			String sql 	 = ("SELECT * FROM Attendance WHERE Attendance.userID = "
							+currentUserID+ " && attending = " +true);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Attendance a 	= new Attendance();
				a.eventId	 	= rs.getInt("eventID");
				attend.add(a);  // add the results to array list to return
			}

			// Get notifications list for the events which are associated to a particular user
			for(int i = 0; i <= attend.size()-1; i++) {

				int eventValue = attend.get(i).eventId;
				String sql2    = ("SELECT * FROM Notification WHERE eventID = " + eventValue);
				ResultSet rs2  = st.executeQuery(sql2);

				while (rs2.next()) {

					Notific n 		 = new Notific();

					n.userId = rs2.getInt("userID");
					n.eventId 		 = eventValue;
					notific.add(n);  // add the results to array list to return
					break;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		closeConn(conn);
		return notific;
	}

	public int login(String userName, String password){
		int res = -1;
		 Connection conn = null;
			// start the connection
			try {
				conn = this.getConnection();
				System.out.println("Connected to database");
			} catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the database");
				e.printStackTrace();
			}
			// get the user list
			try {
				Statement st = (Statement) conn.createStatement();
				String sql = ("SELECT * FROM User");
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					if(rs.getString("userName").equals(userName) && rs.getString("password").equals(password)){
						res = rs.getInt("userID");
						System.out.println("Found");
						break;
					}
				
				}
			} catch (SQLException ex) {
				System.out.println("problem with result set");
			}
			closeConn(conn);

		return res;
	}
	
	/**
	 * Cancel a event
	 * 
	 */
	public void cancelEvent( int eventId, int userId ){
		 Connection conn = null;
		 try {
			 conn = this.getConnection();
			 System.out.println("Connected to database");
		 } catch (SQLException e) {
			 System.out.println("ERROR: Could not connect to the database");
			 e.printStackTrace();
			 return;
		 }
		 try {
			 String sql =
					 "call delete_event("+ eventId +","+ userId +");";
			    Statement st = (Statement) conn.createStatement();
			    st.execute(sql);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 return;
		 }
		closeConn(conn);
	}

	/**
	 * Create a new account on the database, 
	 * this adds the time and room number to the database
	 * 
	 */
	/*public void createAccount(){
		 Connection conn = null;
		 try {
			 conn = this.getConnection();
			 System.out.println("Connected to database");
		 } catch (SQLException e) {
			 System.out.println("ERROR: Could not connect to the database");
			 e.printStackTrace();
			 return;
		 }
		 // Create a table
		 try {
			 String sql =
			 "call insert_user(" +false+",'uyqwe','wefweef','earren','issieonWard','meail@gmail.com',344545345,NULL);";
			    Statement st = (Statement) conn.createStatement();
				boolean j = st.execute(sql);
			 System.out.println( "Account created" );
		 } catch (SQLException e) {
			 System.out.println("ERROR: Could not create the account: " +e);
			 e.printStackTrace();
		 return;
		 }
		closeConn(conn);
	}*/

	
	/**
	 * Create a new event, this adds the time and room number to the database
	 * 
	 */
	public ArrayList<Event> getEvent( int userId){
		ArrayList<Event> event = new ArrayList<Event>();
		Connection conn = null;
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// get the user list
		try {
			Statement st = (Statement) conn.createStatement();
			String sql = ("SELECT * FROM Event WHERE userID =" + userId);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Event e 		= new Event();
				e.userId 		= rs.getInt("userId");
				e.eventId		= rs.getInt("eventId");
				e.eventName 	= rs.getString("eventName");
				e.eventDesc 	= rs.getString("eventDescription");
				e.date			= rs.getString("eventDate");
				e.startTime 	= rs.getString("startTime");
				e.endTime   	= rs.getString("endTime");
				e.location  	= rs.getString("location");
				e.allDayEvent 	= rs.getBoolean("allDayEvent");
				event.add(e); // add the results to array list to return
			}
		} catch (SQLException ex) {
			System.out.println("problem with result set");
		}
		closeConn(conn);
		return event;
	}
	
	
	
	
	/**
	 * Get an Array list of users for use in the invite screen in the view
	 * program returns an Array list of all the employees n the system.
	 * @author dw220
	 */
	public ArrayList<employee> getUserList() {
		
		ArrayList<employee> empArray = new ArrayList<employee>();
		Connection conn = null;
		// start the connection
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		// get the user list
		try {
			Statement st = (Statement) conn.createStatement();
			String sql = ("SELECT * FROM User");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				 employee emp 	 = new employee();	//Employee class to hold information
				 emp.userName    = rs.getString("userName");
				 emp.forename 	 = rs.getString("forename");
				 emp.isSuperUser = rs.getBoolean("isSuperUser");
				 emp.lastName	 = rs.getString("surname");
				 emp.officeNum	 = rs.getInt("officeNum");
				 emp.password	 = rs.getString("password");
				 emp.userID		 = rs.getInt("userID");
				 emp.phoneNum	 = rs.getInt("phoneNum");
				 empArray.add(emp);
			}
		} catch (SQLException ex) {
			System.out.println("no: " +ex);
		}
		
		closeConn(conn);
		return empArray;
	}
	/**
	 * Close the given connection
	 */	
	public void closeConn(Connection c) {
		try {
			c.close();
		} catch ( SQLException e ) {
			System.out.println("Connection not closed");
		}
	}

	/*public static void main (String args[]){
		Model m = new Model();
		m.acceptDecline(1,3,true);
	}*/
}

