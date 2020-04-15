package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import rmi.api.employee;
import view.GlobalVariables;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class CreatEvent extends JFrame {
	private Component component;

	public static void main(String args[]) {
		new CreatEvent();

	}

	JFrame eventWindow = new JFrame();

	JPanel eventPanel = new JPanel();
	JLabel name = new JLabel("Name of Event");
	JTextField completename = new JTextField(10);
	JLabel invite = new JLabel("                         Invite");
	JLabel date = new JLabel("      Date");
	JLabel startTime = new JLabel("                  Start Time");
	JLabel endTime = new JLabel("          End Time    ");
	JTextArea dateArea = new JTextArea("", 2, 6);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	JFormattedTextField dateTextField = new JFormattedTextField(sdf);
	JTextArea startTimeArea = new JTextArea("", 1, 5);
	JLabel space = new JLabel("     ");
	JTextArea endTimeArea = new JTextArea("", 1, 5);
	JLabel space2 = new JLabel("        ");
	JLabel spaceTable = new JLabel("          ");
	JLabel notification = new JLabel("You have a new notification");
	JTextArea completedesception = new JTextArea("Description", 8, 17);
	JTextArea search = new JTextArea("    search    ", 1, 1);
	JTable table;

	DailyController DC = new DailyController();

	ArrayList<employee> people = DC.getUserList();
	String username = "";
	JTable maintable = checklistpeople(username);

	JTextArea location = new JTextArea("    location    ", 4, 17);
	JButton create = new JButton("Create Event");
	boolean locationtype = false;
	boolean datetype = false;
	boolean starttype = false;
	boolean endtype = false;
	boolean completetype = false;

	JButton searchT = new JButton("Find User");
	JButton searchpeople = new JButton("Search People");

	public CreatEvent() {

		super("Add Event");
		eventPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		eventWindow.setSize(420, 320);
		eventWindow.setLocation(200, 200);
		eventWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																				// keep
																				// all
																				// windows
																				// open
		eventWindow.setResizable(false);

		// //////////////////////////////////////////////////////

		eventPanel.add(name);
		name.setFont(new Font("Serif", Font.ITALIC, 16));
		eventPanel.add(completename);
		completename.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				completetype = true;
			}
		});

		eventPanel.add(invite);

		invite.setFont(new Font("Serif", Font.ITALIC, 14));
		date.setFont(new Font("Serif", Font.ITALIC, 14));
		eventPanel.add(date);
		eventPanel.add(startTime);
		startTime.setFont(new Font("Serif", Font.ITALIC, 14));
		eventPanel.add(endTime);
		endTime.setFont(new Font("Serif", Font.ITALIC, 14));

		eventPanel.add(search);
		eventPanel.add(dateTextField);
		eventPanel.add(startTimeArea);
		dateTextField.setColumns(7);
		try {
			MaskFormatter dateMask = new MaskFormatter("####-##-##");
			dateMask.install(dateTextField);

		} catch (ParseException ex) {

		}
		try {
			MaskFormatter timeMask = new MaskFormatter("####-##-##");
			timeMask.install(dateTextField);
			datetype = true;

		} catch (ParseException ex) {

		}

		eventPanel.add(space);
		eventPanel.add(startTimeArea);
		startTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				starttype = true;
			}
		});

		eventPanel.add(space2);
		eventPanel.add(endTimeArea);
		endTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				endtype = true;
			}
		});
		eventPanel.add(searchT);

		// ///////////////////////////////
		eventPanel.add(completedesception);

		completedesception.setFont(new Font("Arial", Font.ITALIC, 12));
		completedesception.setLineWrap(true);
		completedesception.setWrapStyleWord(true);
		search.setFont(new Font("Arial", Font.ITALIC, 10));
		search.setLineWrap(true);
		search.setWrapStyleWord(true);

		searchT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				people = DC.getUserList();
				if (search.getText().equals("") || search.getText() == null) {
					for (int i = 0; i < table.getRowCount(); i++) {
						for (int k = 0; k < table.getColumnCount(); k++) {
							// table.setValueAt(people.get(i).userName, i,k);
							table.setValueAt("", i, k);
							System.out.println("hello Michalis");
						}
					}

				}
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {
						table.setValueAt("", i, k);
					}
				}

				for (Iterator<employee> it = people.iterator(); it.hasNext();) {

					if (!it.next().userName.contains(search.getText()))
						it.remove(); // NOTE: Iterator's remove method, not
										// ArrayList's, is used.
					for (int i = 0; i < table.getRowCount(); i++) {
						for (int k = 0; k < table.getColumnCount(); k++) {
							for (int t = 0; t < people.size(); t++) {
								table.setValueAt(people.get(t).userName, i, k);
								break;
							}
							break;
						}
						break;
					}

				}

			}
		});

		// ///////////////////////////////

		completedesception.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				completedesception.setText("");

			}
		});

		// ////////////////////////////////////////////////////////////////

		eventPanel.add(spaceTable);

		eventPanel.add(maintable);

		JScrollPane stblCheckbox = new JScrollPane(maintable);
		stblCheckbox
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		stblCheckbox.setVisible(true);

		eventPanel.add(stblCheckbox, BorderLayout.CENTER);
		JComboBox actm = new JComboBox();
		actm.addItem("Yes");
		actm.addItem("No");

		TableColumn statusColumn = maintable.getColumnModel().getColumn(1);
		statusColumn.setCellEditor(new DefaultCellEditor(actm));

		// //////////////////////////////////////////////////////
		location.setFont(new Font("Arial", Font.ITALIC, 12));
		location.setLineWrap(true);
		location.setWrapStyleWord(true);
		location.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				location.setText("");
				locationtype = true;
			}
		});
		eventPanel.add(location);

		JPanel south = new JPanel();
		create.setFont(new Font("Arial", Font.ITALIC, 10));

		create.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (completename.getText().equals("")
						|| dateTextField.getText().equals("")
						|| startTimeArea.getText().equals("")
						|| endTimeArea.getText().equals("")
						|| location.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Something missing.Please fill all the data! :)",
							"Warning Message", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Complete! :)",
							"Congratulations, Success",
							JOptionPane.WARNING_MESSAGE);
					int userID = GlobalVariables.userID;
					// Date date= Date.valueOf(dateTextField.getText());
					DailyController DC = new DailyController();

					DC.createEvent(userID, completename.getText(),
							completedesception.getText(),
							dateTextField.getText(), startTimeArea.getText(),
							endTimeArea.getText(), location.getText(), false);
					eventWindow.setVisible(false);

					notification.setFont(new Font("Serif", Font.BOLD, 30));
					PopupFactory factory = PopupFactory.getSharedInstance();
					// Random random = new Random();
					int x = eventWindow.getWidth();
					int y = eventWindow.getHeight();

					final Popup popup = factory.getPopup(component,
							notification, x, y-100);
					popup.show();

					ActionListener hider = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							popup.hide();
						}
					};
					// Hide popup in 5 seconds
					Timer timer = new Timer(5000, hider);
					timer.start();

				}
			}
		});

		south.add(create);

	
		// ///////////////////////////////////////////////////////////////////

		eventWindow.getContentPane().add(eventPanel);
		eventWindow.getContentPane().add(south, BorderLayout.SOUTH);
		eventWindow.setVisible(true);

	}

	public JTable checklistpeople(String username2) {

		people = DC.getUserList();

		String[] tophead = { "Name", "CheckList" };
		Object[][] data = new Object[people.size()][people.size()];

		if (username2.equals("") || username2.equals(null)) {
			for (int i = 0; i <= people.size() - 1; i++) {
				System.out.println("for loop");
				// for (int k=0;k<=tophead.length-1;k++){
				int k = 0;
				data[i][k] = people.get(i).userName;
				k++;
				data[i][k] = "";
			}
		} else if ((!(username2.equals("")))) {
			System.out.println("else if");
			String ActualUser = "";
			System.out.println(people.size());
			for (Iterator<employee> it = people.iterator(); it.hasNext();) {

				if (!it.next().userName.contains(username2))
					it.remove(); // NOTE: Iterator's remove method, not
									// ArrayList's, is used.

				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {
						table.setValueAt(people.get(i).userName, i, k);
					}
				}
				System.out.println("complete blank");
				for (int i = 0; i <= people.size() - 1; i++) {
					System.out.println("for loop 2!");
					
					int k = 0;
					data[i][k] = people.get(i).userName;
					k++;
					data[i][k] = "";
					System.out.println("username");
				}
			}
			//

			//
			System.out.println(people.size());

			table.repaint();
		}

		

		table = new JTable(data, tophead);
		table.setPreferredScrollableViewportSize(new Dimension(150, 100));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.CYAN);
		table.setForeground(Color.blue);
		table.setBackground(Color.LIGHT_GRAY);

		return table;

	}
}