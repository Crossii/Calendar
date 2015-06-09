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
	private User user;
	private Schedules schedules;
	private int selectedRow;
	private int selectedColumn;

	/**
	 *
	 * @param p
	 */
	public KalenderListener(KalenderPanel p, User user) {
		kalPanel = p;
		this.user = user;
		try {
			schedules = new Schedules();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not find a file and path for the schedule");
		}
	}

	/**
	 *
	 * @param p
	 */
	public KalenderListener(KalenderFrame p, User user) {
		kalFrame = p;
		this.user = user;
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
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
			kalPanel.getYear_LBL().setText(""+schedules.getYear());
			kalPanel.getKalender_T().clearSelection();
			selectEvents();
		}
		if(source == kalPanel.getLastMonth_BTN()) {
			try {
				schedules.lastMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the first Month of the year");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
			kalPanel.getYear_LBL().setText(""+schedules.getYear());
			kalPanel.getKalender_T().clearSelection();
			selectEvents();
		}
		if(source == kalPanel.getCurrentMonth_BTN()) {
			try {
				schedules.setToCurrentMonth();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "You are already in the current month");
			}
			refreshTable();
			kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
			kalPanel.getYear_LBL().setText(""+schedules.getYear());
		}
		GregorianCalendar gc = new GregorianCalendar();
		if(schedules.getMonth() == gc.get(GregorianCalendar.MONTH) && schedules.getYear() == gc.get(GregorianCalendar.YEAR)) {
			selectCurrentDayAndEvents();
		}

		//*********************************************************************************** now doing the create, delete and update button

		if(kalPanel.getCreate_BTN().getText() == "Save" && kalPanel.getSetBeginning_BTN().isEnabled()
				&& source == kalPanel.getSetBeginning_BTN() && kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn) != null) {
			kalPanel.getVon_TF().setText(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn).toString());
		}
		if(kalPanel.getCreate_BTN().getText() == "Save" && kalPanel.getSetEnding_BTN().isEnabled()
				&& source == kalPanel.getSetEnding_BTN() && kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn) != null) {
			kalPanel.getBis_TF().setText(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn).toString());
		}

		if(kalPanel.getCreate_BTN() == source) {
			if(kalPanel.getCreate_BTN().getText() == "Save") {
				kalPanel.getCreate_BTN().setText("Create");
				kalPanel.getUpdate_BTN().setEnabled(true);
				kalPanel.getDelete_BTN().setEnabled(true);
				kalPanel.getBeschreibung_LBL().setEnabled(false);
				kalPanel.getSetBeginning_BTN().setEnabled(false);
				kalPanel.getSetEnding_BTN().setEnabled(false);
				if(schedules.isNumeric(kalPanel.getVon_TF().getText().toString()) && schedules.isNumeric(kalPanel.getBis_TF().getText().toString())) {
					int year = Integer.parseInt(kalPanel.getYear_LBL().getText().toString());
					int month = schedules.getMonth(kalPanel.getMonth_LBL().getText().toString());
					int dayStart = Integer.parseInt(kalPanel.getVon_TF().getText().toString());
					int dayEnd = Integer.parseInt(kalPanel.getBis_TF().getText().toString());
					try {
						schedules.addSchedule(new Schedule(year, month, dayStart, year, month, dayEnd, kalPanel.getBeschreibung_LBL().getText().toString(), user));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Event already exists!");
					}
				}
			} else {
				kalPanel.getCreate_BTN().setText("Save");
				kalPanel.getUpdate_BTN().setEnabled(false);
				kalPanel.getDelete_BTN().setEnabled(false);
				kalPanel.getBeschreibung_LBL().setEnabled(true);
				kalPanel.getSetBeginning_BTN().setEnabled(true);
				kalPanel.getSetEnding_BTN().setEnabled(true);
				JOptionPane.showMessageDialog(null, "Please select a day before you press the set button");
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
	public void selectCurrentDayAndEvents() {
		int[] select = schedules.getCurrentDayPosition();
		CostumRenderer cr = new CostumRenderer(schedules.getSchedulesForThisMonth(), select[1], select[0]);
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}
	public void selectEvents() {
		CostumRenderer cr = new CostumRenderer(schedules.getSchedulesForThisMonth());
		kalPanel.getKalender_T().setDefaultRenderer(Object.class, cr);
	}

	public void setSelectedColumnAndRow(int row, int column) {
		selectedRow = row;
		selectedColumn = column;
	}
}
