package mainGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.*;

import common.ListenerSetException;
import model.Users;
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
			System.out.println("Register wurde gedrückt");
			try {
				RegisterFrame register = new RegisterFrame(users.getFileAndPath());
			} catch (UnsupportedLookAndFeelException | ListenerSetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(source == mainPanel.getLogIn_BTN()) {
			System.out.println("Login wurde gedrückt");
			try {
				LogInFrame login = new LogInFrame(users.getFileAndPath(), mainPanel);
			} catch (UnsupportedLookAndFeelException | ListenerSetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Users getUsers() {
		return users;
	}
}
