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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import rmi.client.Client;

public class EditEvent extends JFrame {
	private Component component;

	JFrame eventWindow = new JFrame();

	JPanel eventPanel = new JPanel();
	JLabel name = new JLabel("Name of Event");

	JTextField completename = new JTextField(10);
	JLabel invite = new JLabel("                         Invite");
	JLabel date = new JLabel("      Date");
	JLabel startTime = new JLabel("                  Start Time");
	JLabel endTime = new JLabel("          End Time    ");
	JTextArea dateArea = new JTextArea("", 2, 6);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	JFormattedTextField dateTextField = new JFormattedTextField(sdf);
	JTextArea startTimeArea = new JTextArea("", 1, 5);
	JLabel space = new JLabel("     ");
	JTextArea endTimeArea = new JTextArea("", 1, 5);
	JLabel space2 = new JLabel("  ");
	JLabel spaceTable = new JLabel("          ");
	JTextArea modifyDate = new JTextArea();
	JTextArea fakemodifyDate = modifyDate;
	JTextArea modifystartTime = new JTextArea();
	JTextArea fakemodifystartTime = modifystartTime;
	JTextArea modifyendTime = new JTextArea();
	JTextArea fakemodifyendTime = modifyendTime;
	JLabel notification = new JLabel("You have a new notification");
	JTextArea completedesception = new JTextArea("Description", 8, 17);
	JTextArea fakecompletedesception = completedesception;
	JTextArea search = new JTextArea("    search    ", 1, 1);
	JTextArea name2 = new JTextArea();
	JTextArea fakename2 = new JTextArea();

	JTable table;
	Client rmi = new Client(); // call rmi to have access of changinf Database
	GlobalVariables global;
	JButton goBakc = new JButton("DailyScreen");

	

	String[] people = { "Michalis", "Darren", "Sean", "AAA", "BBB", "AAAAAA",
			"Darren", "Sean", "AAA", "BBB", "AAAAAA" };

	JTextArea location = new JTextArea("    location    ", 4, 17);
	JButton edit = new JButton("Edit Event");
	boolean locationtype = false;
	boolean datetype = false;
	boolean starttype = false;
	boolean endtype = false;
	boolean completetype = false;
	JTable maintable = checklistpeople();

	JButton searchpeople = new JButton("Search People");

	public EditEvent() {

		super("Edit Event");
		eventPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		eventWindow.setSize(420, 320);
		eventWindow.setLocation(200, 200);
		eventWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																				// keep
																				// all
																				// windows
																				// open
		eventWindow.setResizable(false);

		// /////////////////

		// //////////////////////////////////////////////////////

		eventPanel.add(name);
		eventPanel.add(name2);
		name.setFont(new Font("Serif", Font.ITALIC, 16));
		// eventPanel.add(pasingName);
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
		eventPanel.add(modifyDate);
	
		eventPanel.add(modifystartTime);
		dateTextField.setColumns(7);
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.install(dateTextField);

		} catch (ParseException ex) {

		}
		try {
			MaskFormatter timeMask = new MaskFormatter("##/##/####");
			timeMask.install(dateTextField);
			datetype = true;

		} catch (ParseException ex) {

		}

		eventPanel.add(space);
		startTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				starttype = true;
			}
		});

		eventPanel.add(space2);
	
		eventPanel.add(modifyendTime);

		endTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				endtype = true;
			}
		});

		// ///////////////////////////////
		eventPanel.add(completedesception);

		completedesception.setFont(new Font("Arial", Font.ITALIC, 12));
		completedesception.setLineWrap(true);
		completedesception.setWrapStyleWord(true);
		search.setFont(new Font("Arial", Font.ITALIC, 10));
		search.setLineWrap(true);
		search.setWrapStyleWord(true);
		// ///////////////////////////////

		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				search.setText("");

			}
		});

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
		edit.setFont(new Font("Arial", Font.ITALIC, 10));

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if ((locationtype && starttype && endtype && datetype && completetype)) {
					JOptionPane.showMessageDialog(null,
							"Something missing.Please fill all the data! :)",
							"Warning Message", JOptionPane.WARNING_MESSAGE);
				} else {

					JOptionPane.showMessageDialog(null, "Complete! :)",
							"Congratulations, Success",
							JOptionPane.WARNING_MESSAGE);

					eventWindow.setVisible(false);

				}
			}
		});

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int a = 0; a < rmi.getEvent(global.userID).size(); a++) {
					if (rmi.getEvent(global.userID).get(a).eventName
							.equals(fakename2.getText())) {
						System.out.println("Yes");
						rmi.updateEvent(
								rmi.getEvent(global.userID).get(a).userId,
								rmi.getEvent(global.userID).get(a).eventId,
								name2.getText(), completedesception.getText(),
								rmi.getEvent(global.userID).get(a).date,
								rmi.getEvent(global.userID).get(a).startTime,
								rmi.getEvent(global.userID).get(a).endTime,
								rmi.getEvent(global.userID).get(a).location,
								false);

						break;

					} else {
						System.out.println("False");
					}

				}

		

				notification.setFont(new Font("Serif", Font.BOLD, 30));
				PopupFactory factory = PopupFactory.getSharedInstance();
				// Random random = new Random();
				int x = eventWindow.getWidth();
				int y = eventWindow.getHeight();

				final Popup popup = factory.getPopup(component, notification,
						x, y);
				popup.show();

				ActionListener hider = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						popup.hide();
					}
				};
			
				Timer timer = new Timer(5000, hider);
				timer.start();
			}

		});
		
		south.add(edit);

		goBakc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DailyView();
			}
		});
		south.add(goBakc);


		// ///////////////////////////////////////////////////////////////////

		eventWindow.getContentPane().add(eventPanel);
		eventWindow.getContentPane().add(south, BorderLayout.SOUTH);
		eventWindow.setVisible(true);

	}

	public JTable checklistpeople() {

		String[] tophead = { "Name", "CheckList" };
		Object[][] data = new Object[people.length][people.length];

		for (int i = 0; i <= people.length - 1; i++) {
			for (int k = 0; k <= tophead.length - 1; k++) {
				data[i][k] = people[i];
				k++;
				data[i][k] = "";
			}


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
