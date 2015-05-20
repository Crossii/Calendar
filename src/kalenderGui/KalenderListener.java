package kalenderGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

import common.ListenerSetException;
import model.Schedule.Schedules;
import register.RegisterFrame;
import logIn.LogInFrame;
import mainGui.MainFrame;
import model.User.User;

/**
 *
 * VATListener
 *
 */
public class KalenderListener implements ActionListener {

	// Reference to the graphical components
	private KalenderPanel kalPanel;
	private KalenderFrame kalFrame;
	private String fileAndPath;
	private User user;
	private Schedules schedules;

	/**
	 *
	 * @param p
	 */
	public KalenderListener(KalenderPanel p, User user, String fileAndPathSchedule) {
		kalPanel = p;
		this.user = user;
		try {
			schedules = new Schedules(fileAndPathSchedule);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not find a file and path for the schedule");
		}
	}

	/**
	 *
	 * @param p
	 */
	public KalenderListener(KalenderFrame p, User user, String fileAndPath) {
		kalFrame = p;
		this.user = user;
		this.fileAndPath = fileAndPath;
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();


		if(source == kalPanel.getNextMonth_BTN()) {
			try {
				schedules.nextMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the last Month of the year");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText("" + schedules.getCurrentMonth());
			kalPanel.getKalender_T().clearSelection();
		}
		if(source == kalPanel.getLastMonth_BTN()) {
			try {
				schedules.lastMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the first Month of the year");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText(""+schedules.getCurrentMonth());
			kalPanel.getKalender_T().clearSelection();
		}
		if(source == kalPanel.getCurrentMonth_BTN()) {
			try {
				schedules.setToCurrentMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the current month");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText("" + schedules.getCurrentMonth());
		}
		GregorianCalendar gc = new GregorianCalendar();
		if(schedules.getMonth() == gc.get(GregorianCalendar.MONTH)) {
			selectCurrentDay();
		}
	}

	public User getUser() {
		return user;
	}

	public Schedules getSchedules() {
		return schedules;
	}

	public void refreshTable() {
		int p = 0;
		int u = schedules.getDayPerMonth()/7;

		if(u%7 != 0) u++;
		for(int i = 0; i < u; i++) {
			for(int o = 0; o < 7; o++) {
				p++;
				if((schedules.getDayPerMonth() >= p))
					kalPanel.getKalender_T().setValueAt(""+p, i, o);
				else
					kalPanel.getKalender_T().setValueAt("", i, o);
			}
		}
	}

	public void selectCurrentDay() {
		int column = getSchedules().getCurrentColumnDay();
		int row = getSchedules().getCurrentRowDay();
		kalPanel.getKalender_T().setColumnSelectionInterval(column, column);
		kalPanel.getKalender_T().setRowSelectionInterval(row, row);
	}
}
