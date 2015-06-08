package common;

//just for research

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import kalenderGui.KalenderFrame;
import kalenderGui.KalenderPanel;
import mainGui.*;
import model.Schedule.Schedules;
import model.User.Users;
import register.*;
import logIn.*;

public class ApplicationActionListener implements ActionListener {

	private MainFrame parentFrame;
	private MainPanel parentPanel;

	private KalenderFrame parentCalendarFrame;
	private KalenderPanel parentCalendarPanel;
	// exit application with or without question
	private boolean askFor;
	private Users users;
	private Schedules schedules;
	
	/**
	 * 
	 * @param askFor
	 * @param mainFrame
	 */
	public ApplicationActionListener(boolean askFor, MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentFrame=mainFrame;
		users = new Users();
		try {
			schedules = new Schedules();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ApplicationActionListener(boolean askFor, MainFrame parentFrame, MainPanel m) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentFrame=parentFrame;
		users = new Users();
		try {
			schedules = new Schedules();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.parentPanel = m;
	}

	/**
	 * was made so you  can use the bar in the calendar frame
	 * @param askFor
	 * @param mainFrame
	 */
	public ApplicationActionListener(boolean askFor, KalenderFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentCalendarFrame=mainFrame;
		users = new Users();
	}
	public ApplicationActionListener(boolean askFor, KalenderFrame parentFrame, KalenderPanel m) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentCalendarFrame=parentFrame;
		users = new Users();
		this.parentCalendarPanel = m;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(parentFrame != null) {
			if (source == parentFrame.getExitItem()) {
				System.out.println("Exit button wurde gedrueckt!");
				int exit = JOptionPane.OK_OPTION;
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				if (askFor)
					exit = JOptionPane.showConfirmDialog(parentFrame, "Exit the application?", "Exit ...", JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION) System.exit(1);
			}
			if (source == parentFrame.getRegister()) {
				System.out.println("Register button wurde gedrückt!");
				try {
					RegisterFrame register = new RegisterFrame();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}

			}
			if (source == parentFrame.getLogIn()) {
				System.out.println("LogIn button wurde gedrückt!");
				try {
					LogInFrame logIn = new LogInFrame(parentPanel);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(parentCalendarFrame != null) {
			if (source == parentCalendarFrame.getExitItem()) {
				System.out.println("Exit button wurde gedrueckt!");
				int exit = JOptionPane.OK_OPTION;
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				if (askFor)
					exit = JOptionPane.showConfirmDialog(parentFrame, "Exit the application?", "Exit ...", JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION) System.exit(1);
			}
			if (source == parentCalendarFrame.getRegister()) {
				System.out.println("Register button wurde gedrückt!");
				try {
					MainFrame main = new MainFrame();
					RegisterFrame register = new RegisterFrame();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}
				parentCalendarFrame.dispose();
			}
			if (source == parentCalendarFrame.getLogIn()) {
				System.out.println("LogIn button wurde gedrückt!");
				try {
					MainFrame main = new MainFrame();
					LogInFrame logIn = new LogInFrame(main.getMain());
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}
				parentCalendarFrame.dispose();
			}
			if (source == parentCalendarFrame.getLogOut()) {
				System.out.println("Log out button wurde gedrueckt");
				int exit = JOptionPane.OK_OPTION;
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				exit = JOptionPane.showConfirmDialog(parentFrame, "Do you really want to log out?", "Exit ...", JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION) {
					users.logOut();
					parentCalendarFrame.dispose();
					try {
						MainFrame main = new MainFrame();
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					} catch (ListenerSetException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
