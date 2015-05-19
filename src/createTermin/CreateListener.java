package createTermin;

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
public class CreateListener implements ActionListener {

	// Reference to the graphical components
	private CreatePanel mainPanel;
	private Users users;

	/**
	 * 
	 * @param p
	 */
	public CreateListener(CreatePanel p, String fileAndPath) {
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
	
		}
	
	
	public Users getUsers() {
		return users;
	}
}
