package kalenderGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.ListenerSetException;
import register.RegisterFrame;
import logIn.LogInFrame;
import mainGui.MainFrame;
import model.User.User;

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
				RegisterFrame rf = new RegisterFrame(fileAndPath);
				MainFrame mf = new MainFrame(fileAndPath);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
		if(source == kalFrame.getLogIn()) {
			kalFrame.dispose();
			try {
				MainFrame mf = new MainFrame(fileAndPath);
				LogInFrame rf = new LogInFrame(fileAndPath, mf.getMain());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
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
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			} catch (ListenerSetException e1) {
				e1.printStackTrace();
			}
		}
	}

	public User getUser() {
		return user;
	}
}
