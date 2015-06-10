package common;

import model.Schedule.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Made for changing the background color of a cell
 *
 * Created by CrayZay on 22.05.2015.
 */
public class CostumRenderer extends DefaultTableCellRenderer {
    private int row;
    private int column;
    private boolean color;

    private int endRow;
    private int endColumn;

    private int year;

    //*********************************
    private ArrayList<Schedule> events;                  //giving the events in an array
    //*********************************

    public CostumRenderer(int row, int column, boolean color) {
        this.row = row;
        this.column = column;
        this.color = color;

        this.endRow = -1;
        this.endColumn = -1;
    }
    public CostumRenderer(int startRow, int startColumn, int endRow, int endColumn, boolean color) {
        this.row = startRow;
        this.column = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.color = color;
    }

    //*********************************
    public CostumRenderer(int year, ArrayList<Schedule> events, int row, int column, boolean color) {
        this.events = events;
        this.row = row;
        this.column = column;
        this.color = color;
        this.year = year;

        this.endRow = -1;
        this.endColumn = -1;
    }
    public CostumRenderer(ArrayList<Schedule> events) {
        this.events = events;
        color = true;

        this.endRow = -1;
        this.endColumn = -1;
    }
    //*********************************

    public void setColor(boolean color) {
        this.color = color;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(isSelected && hasFocus) {
            c.setBackground(new Color(255, 241, 37));
        }
        else {
            c.setBackground(new Color(255, 255, 255));
            c.setForeground(new Color(0, 0, 0));
        }

        if(table.getValueAt(row, column) != null && events != null)
            if(inbetween(row, column, table))
                c.setBackground(new Color(126, 252, 95));

        if(endRow == -1 && endColumn == -1 && row != 0 && column != 0)
            if ((this.row == row && this.column == column) && color)
                c.setBackground(new Color(252, 131, 1));

        return c;
    }

    public boolean inbetween(int row, int column, JTable table) {
        if(table.getValueAt(row, column) == null)
            return false;

        int dayStart = 0;
        int dayEnd = 0;
        for(Schedule s : events) {
            dayStart = s.getBeginning().getDate();
            dayEnd = s.getEnding().getDate();
            if(dayStart <= Integer.parseInt(table.getValueAt(row, column).toString()) && dayEnd >= Integer.parseInt(table.getValueAt(row, column).toString()) && (1900+s.getBeginning().getYear()) == year && (1900+s.getEnding().getYear()) == year) {
                return true;
            }
        }
        return false;
    }

}
