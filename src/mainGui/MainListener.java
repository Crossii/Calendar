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

	/**
	 * 
	 * @param p
	 */
	public MainListener(MainPanel p, String fileAndPath) {
		mainPanel = p;
		users = new Users(fileAndPath);
		
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();
		
		if(source == mainPanel.getRegister_BTN()) {
			System.out.println("Register wurde gedr�ckt");
			try {
				RegisterFrame register = new RegisterFrame(users.getFileAndPath());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == mainPanel.getLogIn_BTN()) {
			System.out.println("Login wurde gedr�ckt");
			try {
				LogInFrame login = new LogInFrame(users.getFileAndPath(), mainPanel);
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
