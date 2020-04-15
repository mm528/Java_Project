package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

import javax.imageio.ImageIO;
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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.crypto.Data;
import javax.swing.table.DefaultTableModel;

public class WeeklyView extends JFrame {

	JPanel p = new JPanel();
	JPanel topP = new JPanel();
	JPanel generalP = new JPanel();
	JButton zoomIn = new JButton("Z");
	JButton zoomOut = new JButton("z");
	JButton leftI = new JButton("<");
	JButton rightI = new JButton(">");
	static JButton eVent = new JButton();
	JTextField t = new JTextField("Hello, how are you?", 30);
	JTextArea ta = new JTextArea("", 10, 10);
	JLabel labelW = new JLabel("Week 1");
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
	boolean changesize = false;

	JButton exit = new JButton("Exit");

	JComboBox cmbView = new JComboBox();
	int counter = 0;
	int sumSizeH = 0;
	int sumSize = 0;
	int counterC = 0;
	int r = 1;
	int k = 8;

	// //
	public BufferedWriter fileMovements = creatingFile();// CREATING THE FILE
															// (Call the
															// Function to
															// create the file)
	public StringWriter writemike = null;
	// /////

	public int timesofclicks = 0;

	// JOptionPane months = new JOptionPane();

	static JFrame wind = new JFrame("weekly");

	public WeeklyView() {

		super("Weekly View");

		wind.setSize(650, 400);
		wind.setLocation(400, 200);

		wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// no keep
																		// all
																		// windows
																		// open
		wind.setResizable(false);
		// http://png-1.findicons.com/files/icons/1786/oxygen_refit/128/zoom_in.png
		try {
			URL url = new URL(
					"http://png-1.findicons.com/files/icons/1786/oxygen_refit/128/zoom_in.png");// where
																								// we
																								// found
																								// the
																								// pictures
			ImageIcon img = new ImageIcon("res/zoom_in.png");
			zoomIn = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(zoomIn);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		// http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Misc-Zoom-Out-icon.png
		try {
			URL url = new URL(
					"http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Misc-Zoom-Out-icon.png");
			ImageIcon img = new ImageIcon("res/Misc-Zoom-Out-icon.png");
			zoomOut = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			// eVent.setIcon(img);
			p.add(zoomOut);

			// eVent.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}

		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changesize = true;
				counter++;
				try {
					fileMovements.append("  User Zoom In");
					fileMovements.newLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (counter <= 3) {

					table.setFont(new Font("Serif", Font.BOLD, (16 + counter)));
					table.getTableHeader().setFont(
							new Font("Dialog", Font.CENTER_BASELINE,
									10 + counter));

				}
			}

		});

		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changesize == true) {

					try {
						fileMovements.append("  User Zoom Out  ");
						fileMovements.newLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

		// http://www.adweek.com/socialtimes/files/2013/04/delete-300x300.jpg
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

		// p.add(eVent);

		p.add(leftI);

		p.add(labelW);
		p.add(rightI);
		// JOptionPane.showOptionDialog(null,"months",listmoths,0);
		// p.setLayout(new GridLayout(1,20));
		String[] moh = { "January", "February", "March", "April", "May",
				"June", "July", "August", "Semptember", "Octomber", "November",
				"December" };
		final JComboBox actm = new JComboBox(moh);
		p.add(actm);
		// p.add(deleteEvent);

		cmbView.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbView.getSelectedItem().equals("daily")) {
					new DailyView();

				} else if (cmbView.getSelectedItem().equals("monthly")) {
					new MonthlyView();
				}
				wind.dispose();
			}
		});
		p.add(cmbView);

		cmbView.addItem("View");
		cmbView.addItem("daily");
		cmbView.addItem("monthly");

		exit.addActionListener(new ActionListener() {// close the file
			public void actionPerformed(ActionEvent e) {
				try {
					fileMovements.close();
					wind.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		actm.addActionListener(new ActionListener() {// delete everything on the
														// Jtable
			public void actionPerformed(ActionEvent e) {
				String getmont = (String) actm.getSelectedItem();
				if (!(getmont.equals("January"))) {
					for (int i = 0; i < table.getColumnCount(); i++) {
						for (int k = 0; k < table.getRowCount(); k++) {
							table.setValueAt("", k, i);// get sql data! :)
						}
					}
				}
			}
		});

		// /////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////
		JTable tablemain = table();
		p.setLayout(new BorderLayout());

		p.add(tablemain.getTableHeader(), BorderLayout.PAGE_START);
		p.add(tablemain, BorderLayout.CENTER);

		p.add(tablemain);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		wind.getContentPane().add(p);
		p.setBorder(BorderFactory.createLineBorder(Color.black));

		// ///////////////////////////////

		leftI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				timesofclicks = timesofclicks - 1;
				if (timesofclicks == 0 || timesofclicks < 0) {
					if (timesofclicks < 0) {
						labelW.setText("Week1");
						timesofclicks = timesofclicks + 1;
					} else {
						labelW.setText("Week1");
					}
				} else if (timesofclicks == 0) {
					labelW.setText("Week1");

				} else if (timesofclicks == 1) {
					labelW.setText("Week2");

				} else if (timesofclicks == 2) {
					labelW.setText("Week3");

				} else if (timesofclicks == 3) {
					labelW.setText("Week4");

				}

				try {
					fileMovements.append("  User click Left Button  ");
					fileMovements.newLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		rightI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timesofclicks <= 3) {

					timesofclicks = timesofclicks + 1;

				}
				if (timesofclicks == 1) {
					changeHeader();
					labelW.setText("Week2");

				} else if (timesofclicks == 2) {

					labelW.setText("Week3");

				} else if (timesofclicks == 3) {

					labelW.setText("Week4");

				} else {
					timesofclicks = timesofclicks - 1;

				}

				try {
					fileMovements.append("  User click Right Button  ");
					fileMovements.newLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		JPanel south = new JPanel();

		deleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// wind.dispose();
				wind.setLocation(570, 200);
				DeleteEvent goDelete = new DeleteEvent(1000);

			}
		});

		eVent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.setLocation(570, 200);
				// wind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				new CreatEvent();
			}
		});

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {

						MouseListener tableMouseListener = new MouseAdapter() {
							public void mouseCliked(MouseEvent e) {
								int row = table.rowAtPoint(e.getPoint());
								int col = table.columnAtPoint(e.getPoint());

								System.out.println(row + " " + col);
								System.out.println("hello");

							}

						};

					}

				});

		// dailyView.addMouseListener(new MouseAdapter(){
		// public void mouseClicked(MouseEvent me){

		// DailyView goDaily = new DailyView();

		// }

		// });

		// ///Change size of the table(code has to here because Jtable needs to
		// be created first and then change the size.
		for (int q = 0; q <= table.getColumnCount() - 1; q++) {
			TableColumn column = table.getColumnModel().getColumn(q);
			column.setPreferredWidth(90);

		}
		table.setRowHeight(40);
		table.setFont(new Font("Serif", Font.BOLD, 15));

		for (int i = 0; i < table.getColumnCount(); i++) {

			table.getTableHeader()
					.getColumnModel()
					.getColumn(i)
					.setHeaderValue(
							table.getTableHeader().getColumnModel()
									.getColumn(i).getHeaderValue()
									+ "  " + r);
			r++;

		}

		wind.setVisible(true);

		// ////////////////////
	}

	public void deletestff(String j) {

		for (int i = 0; i <= table.getModel().getColumnCount() - 1; i++) {
			for (int k = 0; k <= table.getModel().getRowCount() - 1; k++) {
				if (j.equals(table.getModel().getValueAt(i, k))) {
					table.setValueAt("", i, k);
					System.out.println("Success");

					break;
				}

			}

			table.repaint();
		}

	}

	public JTable table() {
		String[] columnName = { "Monday", "Tuesday", "Wednesday", "Thurday",
				"Friday", "Saturday", "Sunday" };
		Object[][] data = { { "", "", "", "", "", "", "" },
				{ "Meeting 2", "", "", "", "", "", "" },
				{ "", "Meeting 3", "", "", "", "", "" },
				{ "Meeting 1", "", "", "Meeting 4", "", "", "" },
				{ "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "Hello guys!" },
				{ "", "", "", "", "", "", "" }, };

		table = new JTable(data, columnName);
		table.setModel(new DefaultTableModel(data, columnName) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (table.getValueAt(row, col) == ""
						|| table.getValueAt(row, col) == null) {

					new CreatEvent();

				} else
					getH();
				// new EditEvent();
			}
		});

		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.CYAN);
		// table.setBackground(Color.ORANGE);

		JTableHeader header = table.getTableHeader();// get the head and then
														// change color
		header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
		header.setForeground(Color.blue);

		header.setBorder(new CompoundBorder(new MatteBorder(1, 1, 0, 0,
				Color.BLACK), new MatteBorder(2, 1, 1, 1, Color.GRAY)));
		return table;
	}

	WeeklyView jj() {

		return this;
	}

	public BufferedWriter creatingFile() {

		try {
			BufferedWriter tofile = new BufferedWriter(new FileWriter(
					"SaveInformation.txt"));

			return tofile;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void addstaffFile(BufferedWriter file, String detailsofproducts) {
		try {

			file.append("" + detailsofproducts);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void getH() {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String l = "                " + table.getValueAt(row, col).toString();
		String date = "    " + labelW.getText() + "         ";
		String startTime = (String) "      "
				+ table.getTableHeader().getColumnModel().getColumn(col)
						.getHeaderValue();

		String endTime = "";
		if (col == 6) {
			endTime = (String) table.getTableHeader().getColumnModel()
					.getColumn(col).getHeaderValue()
					+ "        ";
		} else {
			endTime = (String) table.getTableHeader().getColumnModel()
					.getColumn(col + 1).getHeaderValue()
					+ "        ";
		}

		EditEvent t = new EditEvent();
		t.modifystartTime.setText(startTime);
		t.modifyendTime.setText(endTime);
		t.modifyDate.setText(date);
		t.name2.setText(l);

	}

	public void changeHeader() {
		for (int i = 0; i <= 6; i++) {

			table.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue("  " + r);
			r++;
			table.repaint();

		}
	}

}
