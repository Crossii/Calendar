package kalenderGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

import common.CostumRenderer;
import model.Schedule.Schedules;
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
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth() + ", " + schedules.getYear());
			kalPanel.getKalender_T().clearSelection();
		}
		if(source == kalPanel.getLastMonth_BTN()) {
			try {
				schedules.lastMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the first Month of the year");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth() + ", " + schedules.getYear());
			kalPanel.getKalender_T().clearSelection();
		}
		if(source == kalPanel.getCurrentMonth_BTN()) {
			try {
				schedules.setToCurrentMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the current month");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth() + ", " + schedules.getYear());
		}
		GregorianCalendar gc = new GregorianCalendar();
		if(schedules.getMonth() == gc.get(GregorianCalendar.MONTH) && schedules.getYear() == gc.get(GregorianCalendar.YEAR)) {
			selectCurrentDay();
		} else {
			deselectCurrentDay();
		}
	}

	public User getUser() {
		return user;
	}

	public Schedules getSchedules() {
		return schedules;
	}

	public void refreshTable() {
		int rowInTable = 0;
		int columnInTable = 0;

		String[][] table = schedules.getTable();
		for(String[] row:table){
			for(String cell:row) {
				kalPanel.getKalender_T().setValueAt(cell, rowInTable, columnInTable);
				columnInTable++;
			}
			columnInTable = 0;
			rowInTable++;
		}
	}

	public void selectCurrentDay() {
		CostumRenderer cr = new CostumRenderer(schedules.getCurrentRowDay(), schedules.getCurrentColumnDay(), true);
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}
	public void deselectCurrentDay(){
		CostumRenderer cr = new CostumRenderer(schedules.getCurrentRowDay(), schedules.getCurrentColumnDay(), false);
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}
}
