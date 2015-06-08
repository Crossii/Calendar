package common;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

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


    //*********************************
    private int[] events;                  //giving the events in an array
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
    public CostumRenderer(int[] events) {
        this.events = events;
        color = true;
    }
    //*********************************

    public void setColor(boolean color) {
        this.color = color;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(endRow == -1 && endColumn == -1) {
            if ((this.row == row && this.column == column) && color) {
                c.setBackground(new Color(252, 131, 1));
            } else
                c.setBackground(table.getBackground());
        }
        /*if(endRow > -1 && endColumn > -1){
            //System.out.println("Row: " + row + " Column: " + column + " this.startRow: " + this.row + " this.startColumn: " + this.column + " this.endRow: " + this.endRow + " this.endColumn: " + this.endColumn);
            if (((this.endRow >= row && this.row <= row) && color) && ((this.endRow == row && this.endColumn > column) || this.endRow > row)
                            && ((this.row == row && this.column < column) || this.row < row)) {
                c.setBackground(new Color(126, 252, 95));
                //System.out.println("this.endRow <= row:"+(this.endRow <= row));
                //System.out.println("Row: " + row + " Column: " + column + " this.startRow: " + this.row + " this.startColumn: " + this.column + " this.endRow: " + this.endRow + " this.endColumn: " + this.endColumn);

            } else
                c.setBackground(table.getBackground());
        }*/
        if(events != null) {
            for(int i = 0; i < events.length; i++) {

            }
        }

        if(isSelected && hasFocus)
            c.setBackground(new Color(255, 241, 37));

        c.setForeground(new Color(45, 59, 255));

        return c;
    }

    public boolean inbetween(int rowPosition1, int columnPosition1, int rowPosition2, int columnPosition2, int row, int column) {
        if (((rowPosition2 >= row && rowPosition1 <= row) && color) && ((rowPosition2 == row && columnPosition2 > column) || rowPosition2 > row)
                && ((rowPosition1 == row && columnPosition1 < column) || rowPosition1 < row)) {
            return true;
        }
        return false;
    }

}
