package logIn;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mainGui.*;

import javax.swing.*;

import kalenderGui.KalenderFrame;
import model.RegistrationException;
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

	/**
	 * 
	 * @param p
	 */
	public LogInListener(LogInPanel p, MainPanel m) {
		panel = p;
		user = new Users();
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
			panel.close();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){	//if enter was pressed, you log in
			logIn();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void logIn() {
		User u = null;
		try {
			u = new User(panel.getMail_CB().getText());
		} catch (RegistrationException e) {
			JOptionPane.showMessageDialog(null, "Problem with E-Mail");
			return;
		}
		if(!user.getUsers().contains(u)) {
			JOptionPane.showMessageDialog(null, "E-Mail does not exist");
			return;
		}
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
				JOptionPane.showMessageDialog(null, "Problem with the user");
			}
		}
		else {
			panel.getPassword_JPF().setText("");
			JOptionPane.showMessageDialog(null, "Wrong password.");
		}
	}
}
