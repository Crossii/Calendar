package common;

import kalenderGui.KalenderListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by CrayZay on 02.06.2015.
 */
public class TableMouseListener implements MouseListener {
    private JTable table;
    private KalenderListener listener;

    public TableMouseListener(JTable table, KalenderListener listener) {
        this.table = table;
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Color c = table.getSelectionBackground();
        //System.out.println(c.toString());
        listener.setSelectedColumnAndRow(table.getSelectedRow(), table.getSelectedColumn());
        listener.changeDescription(table.getSelectedRow(), table.getSelectedColumn());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        listener.setSelectedColumnAndRow(table.getSelectedRow(), table.getSelectedColumn());
        listener.changeDescription(table.getSelectedRow(), table.getSelectedColumn());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
