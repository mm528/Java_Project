package GenViews;
import rmi.api.Event;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class GenMonthly {
	
	private JFrame frame;
	private JButton decMonth;
	private JTextField monthSelect;
	private JButton incMonth;
	private JTable table;
	static ArrayList<Event> e = new ArrayList<Event>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					e = new ArrayList<Event>();
					e.add(new Event());
					e.add(new Event());
					e.get(0).eventName 	 = "hello";
					e.get(0).date 		 = "01-05-2015";
					e.get(0).dayDate	 = 2;
					e.get(0).monthDate	 = 5;
					e.get(1).eventName 	 = "Aza";
					e.get(1).dayDate	 = 3;
					e.get(1).monthDate	 = 5;
					GenMonthly window = new GenMonthly();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GenMonthly() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 545, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton incFont = new JButton("A");
		incFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				C.fontSize++;
				updateFont();
			}
		});
		incFont.setBounds(60, 6, 61, 29);
		
		JButton decFont = new JButton("a");
		decFont.setBounds(6, 6, 61, 29);
		decFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				C.fontSize--;
				updateFont();
			}
		});
		
		JLabel lblMonday = new JLabel("Monday");
		lblMonday.setBounds(15, 53, 49, 16);
		
		JLabel lblTuesday = new JLabel("Tuesday");
		lblTuesday.setBounds(79, 53, 55, 16);
		
		JLabel lblWednesday = new JLabel("Wednesday");
		lblWednesday.setBounds(152, 53, 70, 16);
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setBounds(228, 53, 70, 16);
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setBounds(304, 53, 49, 16);
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setBounds(380, 53, 55, 16);
		
		JLabel lblSunday = new JLabel("Sunday");
		lblSunday.setBounds(456, 53, 45, 16);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 7, 107, 27);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Monthly", "Daily", "Weekly"}));
		comboBox.setMaximumRowCount(3);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(incFont);
		frame.getContentPane().add(lblMonday);
		frame.getContentPane().add(decFont);
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(lblTuesday);
		frame.getContentPane().add(lblWednesday);
		frame.getContentPane().add(lblThursday);
		frame.getContentPane().add(lblFriday);
		frame.getContentPane().add(lblSaturday);
		frame.getContentPane().add(lblSunday);
		
		decMonth = new JButton("<");
		decMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.month--;
				monthSelect.setText(C.day + "-" +C.month+"-"+C.year);
				populateTable();
				if(C.month < 1){
					C.month = 12;
					C.year--;
					monthSelect.setText(C.day + "-" +C.month+"-"+C.year);
				}
				populateTable();
				populateEvents();
			}
		});
		decMonth.setBounds(228, 6, 45, 29);
		frame.getContentPane().add(decMonth);
		
		monthSelect = new JTextField();
		monthSelect.setBounds(272, 5, 134, 28);
		monthSelect.setText(C.day + "-" +C.month+"-"+C.year);
		
		frame.getContentPane().add(monthSelect);
		monthSelect.setColumns(10);
		
		incMonth = new JButton(">");
		incMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.month++;
				monthSelect.setText(C.day + "-" +C.month +"-"+C.year);
				if(C.month > 12){
					C.month = 1;
					C.year++;
					monthSelect.setText(C.day + "-" +C.month+"-"+C.year);
				}
				populateTable();
				populateEvents();
			}
		});
	
		incMonth.setBounds(407, 6, 45, 29);
		frame.getContentPane().add(incMonth);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(new Color(1, 1, 1), 2));
		table.setCellSelectionEnabled(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
			}
		));
		table.setBounds(6, 81, 524, 351);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("*");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  System.out.println("New event");
			}
		});
		btnNewButton.setBounds(469, 6, 61, 29);
		frame.getContentPane().add(btnNewButton);
		for(int i=0; i<C.NoRows; i++){
			table.setRowHeight( i, 60 );
		}
		populateTable();
		populateEvents();
		
	}
	
	public void populateTable(){
		int days 	= getDays();	
		int counter = 0;	
		for(int row=0;row<C.NoRows;row++){
			for(int col=0;col<7;col++){
				if(counter >= days){ table.setValueAt(null,row,col); continue; }
				table.setValueAt(counter % days+1,row,col);
				counter++;
				System.out.println(counter);
			}
		}
	}
	
	public void populateEvents(){
		for(int i=0; i<e.size();i++){
		for(int row=0;row<C.NoRows;row++){
			for(int col=0;col<7;col++){
				if(C.month == e.get(i).monthDate ){
				if(table.getValueAt(row, col) == (Object) e.get(i).dayDate &&
						table.getValueAt(row, col) == (Object) e.get(i).dayDate)
				    { 
					int getValue = (Integer) table.getValueAt(row, col);
					table.setValueAt( (Object) getValue + " \n" + e.get(i).eventName,row,col );					}
				}
			}
		}
	  }
	}
	public void updateFont(){
		table.setFont(new Font("Arial", Font.BOLD, C.fontSize));
	}
	public int getDays(){
		int daysInMonth = 0;
	    Calendar mycal = new GregorianCalendar(C.year, C.month-1, C.day);
		// Get the number of days in that month
		return daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
}

