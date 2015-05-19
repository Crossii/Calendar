package mainGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.ListenerSetException;
import model.User.Users;
import register.*;
import logIn.*;

/**
 * 
 * VATListener
 *
 */
public class MainListener implements ActionListener {

	// Reference to the graphical components
	private MainPanel mainPanel;
	private Users users;
	private String fileAndPathSchedules;

	/**
	 * 
	 * @param p
	 */
	public MainListener(MainPanel p, String fileAndPathUser, String fileAndPathSchedules) {
		mainPanel = p;
		users = new Users(fileAndPathUser);
		this.fileAndPathSchedules = fileAndPathSchedules;
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();
		
		if(source == mainPanel.getRegister_BTN()) {
			System.out.println("Register wurde gedrückt");
			try {
				RegisterFrame register = new RegisterFrame(users.getFileAndPath());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == mainPanel.getLogIn_BTN()) {
			System.out.println("Login wurde gedrückt");
			try {
				LogInFrame login = new LogInFrame(users.getFileAndPath(), fileAndPathSchedules, mainPanel);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Users getUsers() {
		return users;
	}
}
