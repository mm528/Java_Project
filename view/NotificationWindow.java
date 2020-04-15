package view;

import view.GlobalVariables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Database.Model;
import rmi.api.Notific;
import view.DailyController;

public class NotificationWindow extends JFrame {
	static JTable tblnotificatiion;
	static JFrame frmMain = new JFrame();
	static Container pane;
	static DefaultTableModel mtblnotificatiion; // Table model
	static JScrollPane stblnotificatiion; // The scrollpane
	static JPanel pnlnotificatiion;
	DailyController DC = new DailyController();
	ArrayList<Notific> Notification = DC
			.getNotifications(GlobalVariables.userID);
	// String[] Notification = {"New Event","Delete","add"};
	JPanel p = new JPanel();
	Model mod = new Model();
	GlobalVariables global;

	public NotificationWindow() {

		frmMain.setSize(220, 380);
		frmMain.setLocation(280, 200);
		frmMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																			// keep
																			// all
																			// windows
																			// open
		setResizable(false);

		JTable tablemain = NotificationTable();
		p.setLayout(new BorderLayout());

		for (int i = 0; i < mod.getUserList().size(); i++) {
			System.out.println("Michalis" + mod.getUserList().get(i).userID);
			if (!(mod.getUserList().get(i).userID == global.userID)) {
				for (int k = 0; k < mod.getEvent(
						mod.getUserList().get(i).userID).size(); k++) {
					// tablemain.setValueAt(mod.getUserList().get(i).userID, i,
					// 0);
					tablemain.setValueAt(
							mod.getEvent(mod.getUserList().get(i).userID)
									.get(k).eventName, k, 0);

				}

			}
			tablemain.repaint();
		}

		p.add(tablemain.getTableHeader(), BorderLayout.CENTER);

		p.add(tablemain);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		frmMain.add(p);
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i = 0; i < tablemain.getColumnCount(); i++) {
			TableColumn column = tablemain.getColumnModel().getColumn(i);
			column.setPreferredWidth(200);

		}
		tablemain.repaint();
		frmMain.setVisible(true);
	}

	public JTable NotificationTable() {

		String[] tophead = { "Notification" };
		Object[][] data = new String[20][20];

		tblnotificatiion = new JTable(data, tophead);
		tblnotificatiion.setPreferredScrollableViewportSize(new Dimension(200,
				200));
		tblnotificatiion.setFillsViewportHeight(true);
		tblnotificatiion.setShowVerticalLines(true);
		// tblnotificatiion.setGridColor(Color.BLACK);
		tblnotificatiion.setForeground(Color.blue);
		tblnotificatiion.setBackground(Color.white);

		stblnotificatiion = new JScrollPane(tblnotificatiion);
		stblnotificatiion
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		stblnotificatiion.setVisible(true);

		return tblnotificatiion;
	}
}
