package kalenderGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

import common.CostumRenderer;
import model.Schedule.Schedule;
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
			selectRow();
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
			selectRow();
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
		}

		//*********************************************************************************** now doing the create, delete and update button

		/*if(kalPanel.getCreate_BTN().getText() == "Save" && kalPanel.getSetBeginning_BTN().isEnabled()
				&& source == kalPanel.getSetBeginning_BTN()) */


		if(kalPanel.getCreate_BTN() == source) {
			if(kalPanel.getCreate_BTN().getText() == "Save") {
				kalPanel.getCreate_BTN().setText("Create");
				kalPanel.getUpdate_BTN().setEnabled(true);
				kalPanel.getDelete_BTN().setEnabled(true);
				kalPanel.getBeschreibung_LBL().setEnabled(false);
				kalPanel.getSetBeginning_BTN().setEnabled(false);
				kalPanel.getSetEnding_BTN().setEnabled(false);
				System.out.println(kalPanel.getKalender_T().getSelectedColumn() + "; " + kalPanel.getKalender_T().getSelectedRow() + "; ");
			} else {
				kalPanel.getCreate_BTN().setText("Save");
				kalPanel.getUpdate_BTN().setEnabled(false);
				kalPanel.getDelete_BTN().setEnabled(false);
				kalPanel.getBeschreibung_LBL().setEnabled(true);
				kalPanel.getSetBeginning_BTN().setEnabled(true);
				kalPanel.getSetEnding_BTN().setEnabled(true);
			}
		}
		if(kalPanel.getUpdate_BTN() == source) {
			if(kalPanel.getUpdate_BTN().getText() == "Save") {
				kalPanel.getUpdate_BTN().setText("Update");
				kalPanel.getCreate_BTN().setEnabled(true);
				kalPanel.getDelete_BTN().setEnabled(true);
			} else {
				kalPanel.getUpdate_BTN().setText("Save");
				kalPanel.getCreate_BTN().setEnabled(false);
				kalPanel.getDelete_BTN().setEnabled(false);
			}
		}
		if(kalPanel.getDelete_BTN() == source) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure, that you want to delete the event?");
			if(confirm == 0){
				System.out.println("Can't delete");
			}
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
	public void selectRow() {
		CostumRenderer cr = new CostumRenderer(1, 1, 3, 3, true);
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}
	public void selectCurrentDay() {
		int[] select = schedules.getCurrentDayPosition();
		CostumRenderer cr = new CostumRenderer(select[1], select[0], true);
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}
}
