package view;

import static org.junit.Assert.*;

import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

public class EditEventTest {
	EditEvent y= new EditEvent();
	@Before
	public void setUp() throws Exception {
		EditEvent y= new EditEvent();
	}
	
	
	@Test
	public void testInput(){
		
		y.completename.setText("Test");
		assertTrue (y.completename.getText().equals("Test"));
		y.dateArea.setText("2015-04-03");
		assertTrue (y.dateArea.getText().equals("2015-04-03"));
		
		
	}
	@Test
	public void testTable(){
		JTable table = y.table;
		
		table.setValueAt("Test", 0, 0);
		
		y.table.setValueAt("Test", 0, 0);

		for (int i=0; i<table.getRowCount();i++){
			for (int k=0; k<table.getColumnCount();k++){
				assertTrue("Test",table.getValueAt(i, k).equals(y.table.getValueAt(i, k)));
			}
		}
		
	}
	

		
	
	

}
