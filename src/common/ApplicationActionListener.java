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
	private String fileAndPathSchedules;
	
	/**
	 * 
	 * @param askFor
	 * @param mainFrame
	 */
	public ApplicationActionListener(boolean askFor, MainFrame mainFrame, String fileAndPathUser, String fileAndPathSchedules) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentFrame=mainFrame;
		users = new Users(fileAndPathUser);
		this.fileAndPathSchedules = fileAndPathSchedules;
	}
	public ApplicationActionListener(boolean askFor, MainFrame parentFrame, String fileAndPathUser, String fileAndPathSchedules, MainPanel m) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentFrame=parentFrame;
		users = new Users(fileAndPathUser);
		this.parentPanel = m;
		this.fileAndPathSchedules = fileAndPathSchedules;
	}

	/**
	 * was made so you  can use the bar in the calendar frame
	 * @param askFor
	 * @param mainFrame
	 * @param fileAndPathUser
	 * @param fileAndPathSchedules
	 */
	public ApplicationActionListener(boolean askFor, KalenderFrame mainFrame, String fileAndPathUser, String fileAndPathSchedules) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentCalendarFrame=mainFrame;
		users = new Users(fileAndPathUser);
		this.fileAndPathSchedules = fileAndPathSchedules;
	}
	public ApplicationActionListener(boolean askFor, KalenderFrame parentFrame, String fileAndPathUser, String fileAndPathSchedules, KalenderPanel m) {
		// TODO Auto-generated constructor stub
		this.askFor=askFor;
		this.parentCalendarFrame=parentFrame;
		users = new Users(fileAndPathUser);
		this.parentCalendarPanel = m;
		this.fileAndPathSchedules = fileAndPathSchedules;
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
					RegisterFrame register = new RegisterFrame(users.getFileAndPath());
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}

			}
			if (source == parentFrame.getLogIn()) {
				System.out.println("LogIn button wurde gedrückt!");
				try {
					LogInFrame logIn = new LogInFrame(users.getFileAndPath(), fileAndPathSchedules, parentPanel);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}
			}
			if (source == parentFrame.getLogOut()) {
				System.out.println("Log out button wurde gedrueckt");
				int exit = JOptionPane.OK_OPTION;
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				if (askFor)
					exit = JOptionPane.showConfirmDialog(parentFrame, "Do you really want to log out?", "Exit ...", JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION) {
					users.logOut();
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
					MainFrame main = new MainFrame(users.getFileAndPath(), fileAndPathSchedules);
					RegisterFrame register = new RegisterFrame(users.getFileAndPath());
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
					MainFrame main = new MainFrame(users.getFileAndPath(), fileAndPathSchedules);
					LogInFrame logIn = new LogInFrame(users.getFileAndPath(), fileAndPathSchedules, main.getMain());
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
				if (askFor)
					exit = JOptionPane.showConfirmDialog(parentFrame, "Do you really want to log out?", "Exit ...", JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION) {
					users.logOut();
					parentCalendarFrame.dispose();
				}
				try {
					MainFrame main = new MainFrame(users.getFileAndPath(), fileAndPathSchedules);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				} catch (ListenerSetException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
