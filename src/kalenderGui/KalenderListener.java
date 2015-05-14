package kalenderGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.*;

import common.ListenerSetException;
import register.RegisterFrame;
import logIn.LogInFrame;
import mainGui.MainFrame;
import mainGui.MainPanel;
import model.User;
import model.Users;

/**
 * 
 * VATListener
 *
 */
public class KalenderListener implements ActionListener {

	// Reference to the graphical components
	private KalenderPanel kalPanel;
	private KalenderFrame kalFrame;
	private String fileAndPath;
	private User user;

	/**
	 * 
	 * @param p
	 */
	public KalenderListener(KalenderPanel p, User user) {
		kalPanel = p;
		this.user = user;
	}
	
	/**
	 * 
	 * @param p
	 */
	public KalenderListener(KalenderFrame p, User user, String fileAndPath) {
		kalFrame = p;
		this.user = user;
		this.fileAndPath = fileAndPath;
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();
		
		if(source == kalFrame.getRegister()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath);
				RegisterFrame rf = new RegisterFrame(fileAndPath);
			} catch (UnsupportedLookAndFeelException | ListenerSetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(source == kalFrame.getLogIn()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath);
				LogInFrame rf = new LogInFrame(fileAndPath, mf.getMain());
			} catch (UnsupportedLookAndFeelException | ListenerSetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(source == kalFrame.getExitItem()) {
			kalFrame.dispose();
		}

		if(source == kalFrame.getLogOut()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath);
			} catch (UnsupportedLookAndFeelException | ListenerSetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public User getUser() {
		return user;
	}
}
