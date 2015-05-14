package logIn;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainGui.*;

import javax.swing.*;

import kalenderGui.KalenderFrame;
import model.User.User;
import model.User.Users;

/**
 * 
 * SimpleListener
 *
 */
public class LogInListener implements ActionListener {

	// Reference to the graphical components
	private LogInPanel panel;
	private MainPanel main;
	private Users user;

	/**
	 * 
	 * @param p
	 */
	public LogInListener(LogInPanel p, String fileAndPath, MainPanel m) {
		panel = p;
		try {
			user = new Users(fileAndPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		main = m;
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();

		if (source == panel.getReset_BTN()) {
			System.out.println("Log in button wurde gedrueckt!");
			// set default values
			User attempt = user.login(panel.getMail_CB().getText().toString(), panel.getPassword_JPF().getPassword());
			
			if(attempt != null) {
				JOptionPane.showMessageDialog(null, "You have succesfully logged in.");
				main.setBackground(Color.GREEN);
				panel.close();
				main.close();
				
				try {
					kalenderGui.KalenderFrame mf = new KalenderFrame(user.getUsers().get(user.getUsers().indexOf(attempt)), user.getFileAndPath());
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Wrong password.");
			
		}
		if (source == panel.getClose_BTN()) {
			System.out.println("Close button wurde gedrueckt!");
			panel.close();
		}

	}
	
	public Users getInfo() {
		return user;
	}
}
