package logIn;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mainGui.*;

import javax.swing.*;

import kalenderGui.KalenderFrame;
import model.Schedule.Schedules;
import model.User.User;
import model.User.Users;

/**
 * 
 * SimpleListener
 *
 */
public class LogInListener implements ActionListener, KeyListener {

	// Reference to the graphical components
	private LogInPanel panel;
	private MainPanel main;
	private Users user;
	private Schedules schedules;

	/**
	 * 
	 * @param p
	 */
	public LogInListener(LogInPanel p, MainPanel m) {
		panel = p;
		user = new Users();
		try {
			schedules = new Schedules();
		} catch (Exception e) {
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
			logIn();
		}

		if (source == panel.getClose_BTN()) {
			System.out.println("Close button wurde gedrueckt!");
			panel.close();
		}

	}
	
	public Users getInfo() {
		return user;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			logIn();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void logIn() {
		if(user.getUsers().contains(panel.getMail_CB().getText())) {
			JOptionPane.showMessageDialog(null, "E-Mail does not exist");
			return;
		}
		System.out.println("Log in button wurde gedrueckt!");
		// set default values
		User attempt = user.login(panel.getMail_CB().getText().toString(), panel.getPassword_JPF().getPassword());

		if(attempt != null) {
			JOptionPane.showMessageDialog(null, "You have succesfully logged in.");
			main.setBackground(Color.GREEN);
			panel.close();
			main.close();

			try {
				kalenderGui.KalenderFrame mf = new KalenderFrame(user.getUsers().get(user.getUsers().indexOf(attempt)));
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			panel.getPassword_JPF().setText("");
			JOptionPane.showMessageDialog(null, "Wrong password.");
		}
	}
}
