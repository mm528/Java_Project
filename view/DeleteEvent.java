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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import view.GlobalVariables;
import view.DailyView;

public class DeleteEvent extends JFrame {

	JFrame eventWindow = new JFrame();

	JPanel deletePanel = new JPanel();

	JLabel name = new JLabel("Name of Event  ");
	JTextField completename = new JTextField(10);
	JLabel invite = new JLabel(
			"                                                                                           Invite");
	JLabel date = new JLabel("      Date");
	JLabel startTime = new JLabel("                  Start Time");
	JLabel endTime = new JLabel("          End Time    ");
	JTextArea dateArea = new JTextArea("", 2, 6);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	JFormattedTextField dateTextField = new JFormattedTextField(sdf);
	JTextArea startTimeArea = new JTextArea("", 1, 5);
	JLabel space = new JLabel("     ");
	JTextArea endTimeArea = new JTextArea("", 1, 5);
	JLabel space2 = new JLabel("        ");
	JLabel spaceTable = new JLabel("                    ");

	JTextArea completedesception = new JTextArea("Description", 8, 17);
	JTextArea search = new JTextArea("    search    ", 1, 1);
	JTable table;
	JCheckBox michalis = new JCheckBox("");

	String[] people = { "Michalis", "Darren", "Sean" };

	JTextArea location = new JTextArea("    location    ", 4, 17);

	JButton deleteButton = new JButton("Delete");
	boolean locationtype = false;
	boolean datetype = false;
	boolean starttype = false;
	boolean endtype = false;
	boolean completetype = false;
	JTable maintable = checklistpeople();
	JButton searchpeople = new JButton("Search People");
	JButton goBack = new JButton("Previous Screen");
	// DailyView y;
	int eventID;

	public DeleteEvent(int event) {
		super("Delete Event");
		eventID = event;
		deletePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		eventWindow.setSize(420, 320);
		eventWindow.setLocation(60, 200);
		eventWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																				// keep
																				// all
																				// windows
																				// open
		eventWindow.setResizable(false);

		// //////////////////////////////////////////////////////

		deletePanel.add(name);
		deletePanel.add(completename);
		completename.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				completetype = true;
			}
		});

		deletePanel.add(invite);

		invite.setFont(new Font("Serif", Font.ITALIC, 14));
		date.setFont(new Font("Serif", Font.ITALIC, 14));
		deletePanel.add(date);
		deletePanel.add(startTime);
		startTime.setFont(new Font("Serif", Font.ITALIC, 14));
		deletePanel.add(endTime);
		endTime.setFont(new Font("Serif", Font.ITALIC, 14));
		deletePanel.add(search);
		deletePanel.add(dateTextField);
		deletePanel.add(startTimeArea);
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

		deletePanel.add(space);
		deletePanel.add(startTimeArea);
		startTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				starttype = true;
			}
		});

		deletePanel.add(space2);
		deletePanel.add(endTimeArea);
		endTimeArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				endtype = true;
			}
		});

		// ///////////////////////////////
		deletePanel.add(completedesception);

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
		deletePanel.add(location);

		JPanel south = new JPanel();
		// delete.setFont(new Font("Arial", Font.ITALIC, 10));
		deleteButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {
				int delete = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the event?");
				if (delete == JOptionPane.YES_OPTION) {

					eventWindow.setVisible(false);
					WeeklyView j = new WeeklyView().jj();
					DailyController DC = new DailyController();

					DC.cancelEvent(eventID, GlobalVariables.userID); // TODO
					// j.deletestff(completename.getText());

				}
			}
		});

		goBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				eventWindow.dispose();
			}

		});

		south.add(goBack);

		south.add(deleteButton);

		// ///////////////////////////////////////////////////////////////////

		eventWindow.getContentPane().add(deletePanel);
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
				data[i][k] = "YES";
			}

		}
		table = new JTable(data, tophead);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.CYAN);
		table.setForeground(Color.blue);
		table.setBackground(Color.LIGHT_GRAY);

		return table;
	}

}
