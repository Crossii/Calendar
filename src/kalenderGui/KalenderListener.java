package kalenderGui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import common.CostumRenderer;
import model.Schedule.Schedule;
import model.Schedule.Schedules;
import model.User.User;

/**
 * VATListener
 */
public class KalenderListener implements ActionListener {

    // Reference to the graphical components
    private KalenderPanel kalPanel;
    private User user;
    private Schedules schedules;
    private int selectedRow;
    private int selectedColumn;

    /**
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
        Schedule event = todayAnEvent();
        if (event != null) {
            JOptionPane.showMessageDialog(null, "You got an event for today: " + event.getInformation());
        }
    }

    /**
     * Button pressed, ....
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        // gets the source of the component
        Object source = e.getSource();

        Object refresh = null;

        GregorianCalendar gc = new GregorianCalendar();

        Schedule s = null;

        if(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn) != null) {
            try {
                s = new Schedule(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), Integer.parseInt(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn).toString()), "", user);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if(source == kalPanel.getRefresh_BTN()) {
            refresh = source;
            source = kalPanel.getNextMonth_BTN();
        }

        if (source == kalPanel.getNextMonth_BTN()) {

            try {
                schedules.nextMonth();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "You are already in the last Month of the year");
            }
            refreshTable();
            kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
            kalPanel.getYear_LBL().setText("" + schedules.getYear());
            kalPanel.getKalender_T().clearSelection();
            selectEvents();
        }

        if(refresh != null)
            source = kalPanel.getLastMonth_BTN();

        if (source == kalPanel.getLastMonth_BTN()) {
            try {
                schedules.lastMonth();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "You are already in the first Month of the year");
            }
            refreshTable();
            kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
            kalPanel.getYear_LBL().setText("" + schedules.getYear());
            kalPanel.getKalender_T().clearSelection();
            selectEvents();
        }
        if (source == kalPanel.getCurrentMonth_BTN()) {
            try {
                schedules.setToCurrentMonth();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "You are already in the current month");
            }
            refreshTable();
            kalPanel.getMonth_LBL().setText(schedules.getCurrentMonth());
            kalPanel.getYear_LBL().setText("" + schedules.getYear());
        }

        //*********************************************************************************** now doing the create, delete and update button

        if (kalPanel.getCreate_BTN().getText() == "Save" && kalPanel.getSetBeginning_BTN().isEnabled()
                && source == kalPanel.getSetBeginning_BTN() && kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn) != null) {
            kalPanel.getVon_TF().setText(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn).toString());
        }
        if (kalPanel.getCreate_BTN().getText() == "Save" && kalPanel.getSetEnding_BTN().isEnabled()
                && source == kalPanel.getSetEnding_BTN() && kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn) != null) {
            kalPanel.getBis_TF().setText(kalPanel.getKalender_T().getValueAt(selectedRow, selectedColumn).toString());
        }

        if (kalPanel.getCreate_BTN() == source) {
            if (kalPanel.getCreate_BTN().getText() == "Save") {
                kalPanel.getCreate_BTN().setText("Create");
                kalPanel.getUpdate_BTN().setEnabled(true);
                kalPanel.getDelete_BTN().setEnabled(true);
                kalPanel.getDelete_BTN().setText("Delete");
                kalPanel.getBeschreibung_LBL().setEnabled(false);
                kalPanel.getSetBeginning_BTN().setEnabled(false);
                kalPanel.getSetEnding_BTN().setEnabled(false);
                if (schedules.isNumeric(kalPanel.getVon_TF().getText().toString()) && schedules.isNumeric(kalPanel.getBis_TF().getText().toString())) {
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
                kalPanel.getDelete_BTN().setText("Cancel");
                kalPanel.getBeschreibung_LBL().setEnabled(true);
                kalPanel.getSetBeginning_BTN().setEnabled(true);
                kalPanel.getSetEnding_BTN().setEnabled(true);
                JOptionPane.showMessageDialog(null, "Please select a day before you press the set button");
            }
        }
        if (kalPanel.getUpdate_BTN() == source) {
            if (kalPanel.getUpdate_BTN().getText() == "Save") {
                kalPanel.getUpdate_BTN().setText("Update");
                kalPanel.getDelete_BTN().setText("Delete");
                kalPanel.getCreate_BTN().setEnabled(true);
                kalPanel.getDelete_BTN().setEnabled(true);
                kalPanel.getBeschreibung_LBL().setEnabled(false);


                for (Schedule p : schedules.getSchedules()) {
                    if (p.isInbetween(s)) {
                        try {
                            s.setBeginning(p.getBeginning());
                            s.setEnding(p.getEnding());
                            s.setInformation(kalPanel.getBeschreibung_LBL().getText());
                            s.setUser(user);
                            schedules.changeSchedule(p,s);
                            return;
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else {
                kalPanel.getUpdate_BTN().setText("Save");
                kalPanel.getDelete_BTN().setText("Cancel");
                kalPanel.getCreate_BTN().setEnabled(false);
                kalPanel.getBeschreibung_LBL().setEnabled(true);
            }
        }
        if (kalPanel.getDelete_BTN() == source) {
            if (kalPanel.getDelete_BTN().getText() == "Delete") {
                for(Schedule schedule: schedules.getSchedules()) {
                    if(schedule.isInbetween(s)) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure, that you want to delete the event?");
                        if (confirm == 0) {
                            try {
                                schedules.deleteSchedule(schedule);
                                break;
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        if (kalPanel.getDelete_BTN() == source && kalPanel.getDelete_BTN().getText() == "Cancel" &&
                    (kalPanel.getUpdate_BTN().getText() == "Save" || kalPanel.getCreate_BTN().getText() == "Save")) {
            kalPanel.getSetBeginning_BTN().setEnabled(false);
            kalPanel.getSetEnding_BTN().setEnabled(false);
            kalPanel.getBeschreibung_LBL().setEnabled(false);
            kalPanel.getCreate_BTN().setEnabled(true);
            kalPanel.getUpdate_BTN().setEnabled(true);
            kalPanel.getDelete_BTN().setEnabled(true);
            kalPanel.getCreate_BTN().setText("Create");
            kalPanel.getUpdate_BTN().setText("Update");
            kalPanel.getDelete_BTN().setText("Delete");
        }
        if ((gc.get(GregorianCalendar.MONTH)) == schedules.getMonth())
            selectCurrentDayAndEvents(true);
        else
            selectCurrentDayAndEvents(false);
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
        for (String[] row : table) {
            for (String cell : row) {
                kalPanel.getKalender_T().setValueAt(cell, rowInTable, columnInTable);
                columnInTable++;
            }
            columnInTable = 0;
            rowInTable++;
        }
    }

    public void selectCurrentDayAndEvents(boolean color) {
        int[] select = schedules.getCurrentDayPosition();
        CostumRenderer cr = new CostumRenderer(schedules.getYear(), schedules.getSchedulesForThisMonth(), select[1], select[0], color);
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

    public Schedule todayAnEvent() {
        ArrayList<Schedule> events = schedules.getSchedulesForThisMonth();
        GregorianCalendar gc = new GregorianCalendar();
        Schedule today = null;
        try {
            today = new Schedule(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE), "", user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (today != null) {
            for (Schedule s : events) {
                if (s.isInbetween(today)) {
                    return s;
                }
            }
        }
        return null;
    }

    public void changeDescription(int dayRow, int dayColumn) {
        GregorianCalendar gc = new GregorianCalendar();
        if (kalPanel.getCreate_BTN().getText() != "Save" && kalPanel.getKalender_T().getValueAt(dayRow, dayColumn) != null) {
            Schedule today = null;
            try {
                today = new Schedule(Integer.parseInt(kalPanel.getYear_LBL().getText()), schedules.getMonth(kalPanel.getMonth_LBL().getText()), Integer.parseInt(kalPanel.getKalender_T().getValueAt(dayRow, dayColumn).toString()), "", user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            kalPanel.getVon_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
            kalPanel.getBis_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
            kalPanel.getBeschreibung_LBL().setText("Nothing");

            for (Schedule s : schedules.getSchedulesForThisMonth()) {
                if (s.isInbetween(today)) {
                    kalPanel.getVon_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
                    kalPanel.getBis_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
                    kalPanel.getBeschreibung_LBL().setText(s.getInformation());
                    break;
                }
            }
            if (Integer.parseInt(kalPanel.getYear_LBL().getText()) == gc.get(GregorianCalendar.YEAR) && schedules.getMonth(kalPanel.getMonth_LBL().getText()) == gc.get(GregorianCalendar.MONTH)
                    && Integer.parseInt(kalPanel.getKalender_T().getValueAt(dayRow, dayColumn).toString()) == gc.get(GregorianCalendar.DATE)) {
                kalPanel.getVon_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
                kalPanel.getBis_TF().setText("" + kalPanel.getKalender_T().getValueAt(dayRow, dayColumn));
                String description = kalPanel.getBeschreibung_LBL().getText();
                if(!description.contains(", Today")) {
                    description = kalPanel.getBeschreibung_LBL().getText() + ", Today";
                } else {
                    description = "";
                }
                kalPanel.getBeschreibung_LBL().setText(description);
            }
        }
    }
}
