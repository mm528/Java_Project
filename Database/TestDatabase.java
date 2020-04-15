package Database;
import static org.junit.Assert.*;

import rmi.api.Invite;
import rmi.api.Notific;
import org.junit.Test;
import java.sql.*;
import java.util.ArrayList;


public class TestDatabase {

    @Test
     //Testing two methods:
     //getEvent(); createEvent();
    public void testGetEventAndCreateEvent(){
        Model m = new Model();
        int oldSize = m.getEvent(2).size();
        m.createEvent((short)2, "Testing", "...","2015-04-29","08:00:00","16:00:00", "WQ565", false);
        assertEquals(oldSize+1,m.getEvent(2).size());
        assertTrue(m.getEvent(2).get(m.getEvent(2).size() - 1).eventName.equals("Testing"));
    }

    @Test
    // Testing three methods:
    // setReminder(); getReminder(); deleteReminder();
    public void testReminders(){
        Model m = new Model();

        m.setReminder((short)1,3,(byte)4);
        assertEquals(m.getReminder((short)1).get(0).userID1,1);
        assertEquals(m.getReminder((short)1).get(0).eventID1,3);
        assertEquals(m.getReminder((short)1).get(0).reminderTime1,4);

        m.deleteReminder((short)1,3,(byte)4);
        assertEquals(0, m.getReminder((short) 1).size());
    }

    @Test
    // Testing three methods:
    // inviteUser(); getInvites(); undoInvite();
    public void testInvite(){
        Model m = new Model();

        m.inviteUser((short)1,4);
        boolean test = false;
        for(Invite inv: m.getInvite((short)1)) if(inv.userID1 == 1 && inv.eventID1 == 4) test = true;
        assert test;

        m.undoInvite((short)1,4);
        for(Invite inv: m.getInvite((short)1))
            if(inv.userID1 ==1 && inv.eventID1 == 4) assert false;
        else assert true;
    }

    /*@Test
    // Testing one method
    // getUserList();
    public void testGetUserList(){
        Model m = new Model();
        int oldSize = m.getUserList().size();
        m.createAccount();
        assertEquals(oldSize + 1, m.getUserList().size());
        assertEquals("uyqwe",m.getUserList().get(m.getUserList().size()-1).userName);
        m.deleteUser("uyqwe");
    }*/

}

