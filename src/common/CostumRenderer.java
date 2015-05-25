package common;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by CrayZay on 22.05.2015.
 */
public class CostumRenderer extends DefaultTableCellRenderer {
    private int row;
    private int column;
    private boolean color;

    public CostumRenderer(int row, int column, boolean color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if((this.row == row && this.column == column) && color) {
            c.setBackground(new Color(255, 139, 32));
            c.setForeground(new Color(47, 39, 255));
        }
        else
            c.setBackground(table.getBackground());

        return c;
    }
}
