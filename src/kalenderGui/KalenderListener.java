package kalenderGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		if(source == kalFrame.getRegister()) {
			kalFrame.dispose();
			try {
				RegisterFrame rf = new RegisterFrame(fileAndPath);
				MainFrame mf = new MainFrame(fileAndPath, schedules.getFileAndPath());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == kalFrame.getLogIn()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath, schedules.getFileAndPath());
				LogInFrame rf = new LogInFrame(fileAndPath, schedules.getFileAndPath(), mf.getMain());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}

		}
		if(source == kalFrame.getExitItem()) {
			kalFrame.dispose();
		}

		if(source == kalFrame.getLogOut()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath, schedules.getFileAndPath());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
	}

	public User getUser() {
		return user;
	}

	public Schedules getSchedules() {
		return schedules;
	}
}
