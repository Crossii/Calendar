package common;

//just for research

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import mainGui.*;
import model.User.Users;
import register.*;
import logIn.*;

public class ApplicationActionListener implements ActionListener {

	private MainFrame parentFrame;
	private MainPanel m;
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
		this.m = m;
		this.fileAndPathSchedules = fileAndPathSchedules;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source == parentFrame.getExitItem()) {
	        System.out.println("Exit button wurde gedrueckt!");
	        int exit=JOptionPane.OK_OPTION;
	        JOptionPane.setDefaultLocale(Locale.ENGLISH);
	        if (askFor) exit=JOptionPane.showConfirmDialog(parentFrame, "Exit the application?","Exit ...",JOptionPane.YES_NO_OPTION);
	        if (exit==JOptionPane.OK_OPTION) System.exit(1);
		}		
		if(source == parentFrame.getRegister()) {
	        System.out.println("Register button wurde gedrückt!");
			try {
				RegisterFrame register = new RegisterFrame(users.getFileAndPath());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}

		}
		if(source == parentFrame.getLogIn()) {
			System.out.println("LogIn button wurde gedrückt!");
			try {
				LogInFrame logIn = new LogInFrame(users.getFileAndPath(), fileAndPathSchedules, m);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == parentFrame.getLogOut()) {
			System.out.println("Log out button wurde gedrueckt");
			int exit=JOptionPane.OK_OPTION;
			JOptionPane.setDefaultLocale(Locale.ENGLISH);
        	if (askFor) exit=JOptionPane.showConfirmDialog(parentFrame, "Do you really want to log out?","Exit ...",JOptionPane.YES_NO_OPTION);
	        if (exit==JOptionPane.OK_OPTION)  {
	        	users.logOut();
	        }
		}
	}
}
