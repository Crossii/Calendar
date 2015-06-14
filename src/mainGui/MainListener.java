package mainGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.ListenerSetException;
import model.Schedule.Schedules;
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
	private Schedules schedules;

	/**
	 * 
	 * @param p
	 */
	public MainListener(MainPanel p) throws Exception {
		mainPanel = p;
		users = new Users();
		schedules = new Schedules();
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();
		
		if(source == mainPanel.getRegister_BTN()) {
			try {
				RegisterFrame register = new RegisterFrame();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == mainPanel.getLogIn_BTN()) {
			try {
				LogInFrame login = new LogInFrame(mainPanel);
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
