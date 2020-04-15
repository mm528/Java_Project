package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static java.awt.GraphicsDevice.WindowTranslucency.*;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MonthlyView extends JFrame {

	JPanel p = new JPanel();
	JPanel topP = new JPanel();
	JPanel generalP = new JPanel();
	JButton b = new JButton("Z");
	JButton b2 = new JButton("z");
	JButton leftI = new JButton("<");
	JButton rightI = new JButton(">");
	static JButton eVent = new JButton("Event +");
	JComboBox admincmb = new JComboBox();
	JTextField t = new JTextField("Hello, how are you?", 30);
	JTextArea ta = new JTextArea("", 10, 10);
	JLabel labelW = new JLabel("Monday");
	JLabel labelspac = new JLabel(
			"                                                                       ");
	JLabel labelmonday = new JLabel("Monday    ");
	JLabel labeltuesday = new JLabel("Tuesday    ");
	JLabel labelwed = new JLabel("Wedsday   ");
	JLabel labelthur = new JLabel("Thurday   ");
	JLabel labelfrt = new JLabel("Friday   ");
	JLabel labelsat = new JLabel("Saturday   ");
	JLabel labelSunday = new JLabel("Sunday    ");
	JLabel labelspace = new JLabel("        ");
	JTable table;
	JButton deleteEvent = new JButton("Delete Event");
	JButton exit = new JButton();
	JScrollPane scrolltable;
	boolean click = false;
	boolean superuser = false;
	JButton weekly = new JButton("Weekly");
	JComboBox cmbView = new JComboBox();
	LoginData y;
	int counterClicks = 0;
	int days = 0;

	public int timesofclicks = 0;
	public int rightclicks = 0;
	public int leftclicks = 0;
	public int calPage = 0; // Update- sean
	public int realyear, realmonth;
	public int selectedyear;
	public int previousyear, nextyear;
	boolean changesize = false;
	int counter = 0;

	// JOptionPane months = new JOptionPane();

	static JFrame wind = new JFrame("Monthly View");

	JButton btnNextCal = new JButton("next page"); // Update- sean
	JButton btnPrevCal = new JButton("prev page"); // Update- sean
	private final JButton btnEditEvent = new JButton("Edit Event");
	private final JComboBox CmbUserSelect = new JComboBox();
	private final JButton btnNotifications = new JButton("Notification");

	public MonthlyView() {

		super("Monthly View");

		wind.setSize(650, 390);
		wind.setLocation(500, 200);
		wind.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																		// keep
																		// all
																		// windows
																		// open
		wind.setResizable(false);

		GregorianCalendar cal = new GregorianCalendar();
		realyear = cal.get(GregorianCalendar.YEAR);
		realmonth = cal.get(GregorianCalendar.MONTH);
		selectedyear = realyear;
		p.setLayout(null);
		b.setBounds(78, 6, 39, 25);

		try {
			URL url = new URL(
					"http://png-1.findicons.com/files/icons/1786/oxygen_refit/128/zoom_in.png");
			ImageIcon img = new ImageIcon("res/zoom_in.png");
			b = new JButton(new ImageIcon(((img).getImage()).getScaledInstance(
					40, 40, java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(b);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		// p.add(b);
		b2.setBounds(122, 6, 39, 25);
		// p.add(b2);

		try {
			URL url = new URL(
					"http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Misc-Zoom-Out-icon.png");
			ImageIcon img = new ImageIcon("res/Misc-Zoom-Out-icon.png");
			b2 = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(b2);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		eVent.setBounds(166, 6, 75, 25);

		try {
			URL url = new URL(
					"http://png-5.findicons.com/files/icons/99/office/128/add2.png");
			ImageIcon img = new ImageIcon("res/add2.png");
			eVent = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(eVent);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		try {
			URL url = new URL(
					"http://www.adweek.com/socialtimes/files/2013/04/delete-300x300.jpg");
			ImageIcon img = new ImageIcon("res/delete-300x300.jpg");
			deleteEvent = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(deleteEvent);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		// http://dontgetstuck.net/wp-content/uploads/2012/06/Exit-Planning-300x300.png
		try {
			URL url = new URL(
					"http://dontgetstuck.net/wp-content/uploads/2012/06/Exit-Planning-300x300.png");
			ImageIcon img = new ImageIcon("res/Exit-Planning-300x300.png");
			exit = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(exit);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
			}
		});

		leftI.setBounds(246, 6, 41, 25);
		p.add(leftI);
		labelW = new JLabel(Integer.toString(realyear));
		labelW.setBounds(292, 10, 44, 16);

		p.add(labelW);
		rightI.setBounds(341, 6, 41, 25);
		p.add(rightI);
		JComboBox cmbYear = new JComboBox();

		for (int i = realyear - 100; i <= realyear + 100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}
		cmbYear.setBounds(387, 7, 66, 22);
		cmbYear.setSelectedItem(String.valueOf(realyear)); // Select the correct
															// year in the combo
															// box

		p.add(cmbYear);
		// admincmb.setBounds(100,2,40,20);
		// p.add(admincmb);
		getlist(superuser);
		// admincmb.setVisible(superuser);

		p.add(btnPrevCal);
		p.add(btnNextCal);
		p.add(CmbUserSelect);
		CmbUserSelect.addItem("Juanuary");
		CmbUserSelect.addItem("February");
		CmbUserSelect.addItem("March");
		CmbUserSelect.addItem("April");
		CmbUserSelect.addItem("May");
		CmbUserSelect.addItem("June");
		CmbUserSelect.addItem("July ");
		CmbUserSelect.addItem("August");
		CmbUserSelect.addItem("Semptember");
		CmbUserSelect.addItem("Octomber");
		CmbUserSelect.addItem("November");
		CmbUserSelect.addItem("December");

		// p.add(btnEditEvent);
		p.add(btnNotifications);
		cmbView.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbView.getSelectedItem().equals("weekly")) {
					new WeeklyView();

				} else if (cmbView.getSelectedItem().equals("daily")) {
					new DailyView();

				}
				wind.dispose();
			}
		});
		p.add(cmbView);
		// p.add(weekly);

		cmbView.addItem("View");
		cmbView.addItem("weekly");
		cmbView.addItem("daily");

		// ///////////////////////////////////////////////////////////////

		JTable tablemain = table();

		p.add(tablemain.getTableHeader(), BorderLayout.PAGE_START);
		p.add(tablemain, BorderLayout.CENTER);
		p.add(tablemain);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		// scrolltable.setRowHeaderView(table);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		wind.getContentPane().add(p);
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		btnPrevCal.setBounds(217, 155, 89, 25);

		btnNextCal.setBounds(123, 155, 89, 25);

		btnNextCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (calPage < 1) {

					calPage = calPage + 1;

					header();
				}
			}
		});
		weekly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeeklyView();
			}
		});

		btnEditEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new EditEvent();

			}
		});

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changesize = true;
				counter++;

				if (counter <= 3) {

					table.setFont(new Font("Serif", Font.BOLD, (16 + counter)));
					table.getTableHeader().setFont(
							new Font("Dialog", Font.CENTER_BASELINE,
									10 + counter));

				}
			}

		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changesize == true) {

					if (counter >= 0) {
						if (counter == 3) {
							table.setFont(new Font("Serif", Font.BOLD, (19 - 1)));

							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											13 - 1));

						} else if (counter == 2) {
							table.setFont(new Font("Serif", Font.BOLD, (18 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											12 - 1));

						} else if (counter == 1) {
							table.setFont(new Font("Serif", Font.BOLD, (17 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											11 - 1));

						}
						counter--;
					}

				}
			}
		});

		eVent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.setLocation(570, 200);
				// wind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				new CreatEvent();
			}
		});

		btnPrevCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calPage >= 0) {
					calPage = calPage - 1;
					header();
				}
			}
		});

		// ///////////////////////////////

		// Update- sean
		btnNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NotificationWindow();
			}
		});

		leftI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				leftclicks = leftclicks++;

				if (leftclicks == 0) {
					selectedyear = selectedyear - 1;
					labelW.setText(Integer.toString(selectedyear));

				}

				if (leftclicks > 0) {
					for (int i = 0; i < leftclicks; i++) {

						selectedyear = selectedyear - i;
						labelW.setText(Integer.toString(selectedyear));
					}
				}

			}

		});

		rightI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightclicks = rightclicks++;

				if (rightclicks == 0) {
					selectedyear = selectedyear + 1;
					labelW.setText(Integer.toString(selectedyear));

				}

				if (rightclicks > 0) {
					for (int i = 0; i < rightclicks; i++) {

						selectedyear = selectedyear + i;
						labelW.setText(Integer.toString(selectedyear));
					}
				}

			}

		});

		deleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// new DeleteEvent();

			}
		});

		for (int q = 0; q <= table.getColumnCount() - 1; q++) {
			TableColumn column = table.getColumnModel().getColumn(q);
			column.setPreferredWidth(90);

		}
		table.setRowHeight(40);
		table.setFont(new Font("Serif", Font.BOLD, 15));
		// System.out.println(motis.getEvent(1).get(0).location);

		CmbUserSelect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {
						table.setValueAt("", i, k);
					}
				}

				if (CmbUserSelect.getSelectedItem().equals("Juanuary")) {
					days = 30;
				} else if (CmbUserSelect.getSelectedItem().equals("February")) {
					days = 28;

				} else if (CmbUserSelect.getSelectedItem().equals("March")) {
					days = 31;
				} else if (CmbUserSelect.getSelectedItem().equals("April")) {
					days = 30;
				} else if (CmbUserSelect.getSelectedItem().equals("May")) {
					days = 31;
				} else if (CmbUserSelect.getSelectedItem().equals("June")) {
					days = 30;
				} else if (CmbUserSelect.getSelectedItem().equals("July")) {
					days = 31;
				} else if (CmbUserSelect.getSelectedItem().equals("August")) {
					days = 31;
				} else if (CmbUserSelect.getSelectedItem().equals("Semptember")) {
					days = 30;
				}

				else if (CmbUserSelect.getSelectedItem().equals("October")) {
					days = 31;
				} else if (CmbUserSelect.getSelectedItem().equals("November")) {
					days = 30;
				} else if (CmbUserSelect.getSelectedItem().equals("December")) {
					days = 31;
				}

				int r = 1;
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {

						if (CmbUserSelect.getSelectedItem().equals("Juanuary")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}

						else if (CmbUserSelect.getSelectedItem().equals(
								"February")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"March")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"April")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem()
								.equals("May")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"June")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}

						else if (CmbUserSelect.getSelectedItem().equals("July")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"August")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"September")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"October")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"November")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (CmbUserSelect.getSelectedItem().equals(
								"December")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}
						r++;
					}

				}
			}

		});

		table.setBackground(Color.WHITE);

		wind.setVisible(true);

		// ////////////////////
	}

	public void deletestff(String j) {

		for (int i = 0; i <= table.getModel().getColumnCount() - 1; i++) {
			for (int k = 0; k <= table.getModel().getRowCount() - 1; k++) {
				if (j.equals(table.getModel().getValueAt(i, k))) {
					table.setValueAt("", i, k);
					System.out.println("Success");
					System.out.println("Success:)");
					break;
				}

			}

		}

		table.repaint();
	}

	public JTable table() {

		// Update- sean

		String columnName[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday", "Sunday" };

		Object[][] data = { { "1", "2", "3", "4", "5", "6", "7" },
				{ "8", "9", "10", "11", "12", "13", "14" },
				{ "15", "16", "17", "18", "19", "20", "21", },
				{ "22", "23", "24", "25", "26", "27", "28" },
				{ "29", "30", "31", "", "", "", "" },

		};

		table = new JTable(data, columnName);
		table.setModel(new DefaultTableModel(data, columnName) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				counterClicks++;
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (table.getValueAt(row, col) == ""
						|| table.getValueAt(row, col) == null) {
					if (counterClicks == 2) {
						new CreatEvent();
						counterClicks = 0;
					}
				} else if (counterClicks == 2) {

					DailyView dailyscreen = new DailyView();
					dailyscreen.daynumber.setText(table.getValueAt(row, col)
							.toString());
					dailyscreen.labelW.setText(table.getColumnName(col)
							.toString());
					dailyscreen.monthlyscreendata.setText(CmbUserSelect
							.getSelectedItem().toString());

					counterClicks = 0;

				}

			}
		});

		table.setBounds(41, 70, 450, 80);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.black);
		JTableHeader header = table.getTableHeader();// get the head and then
														// change color

		header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
		header.setForeground(Color.blue);

		header.setBorder(new CompoundBorder(new MatteBorder(1, 1, 0, 0,
				Color.BLACK), new MatteBorder(2, 1, 1, 1, Color.GRAY)));

		return table;
	}

	public JTableHeader header() {
		JTableHeader header = table.getTableHeader();// get the head and then
														// change color
		if (calPage == 0) {

			header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
			header.setForeground(Color.blue);

		} else if (calPage == 1) {

			header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
			header.setForeground(Color.blue);

		}

		table.getTableHeader().repaint();
		return header;

	}

	public JComboBox getlist(boolean j) {

		if ((j == true) || (y.getState() == true)) {
			admincmb.addItem("hello");
			admincmb.setVisible(true);
			p.add(admincmb);
			admincmb.setBounds(100, 200, 40, 20);

			return admincmb;
		} else {
			admincmb.setVisible(false);
			return admincmb;

		}
	}
};
