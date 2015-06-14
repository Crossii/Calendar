package kalenderGui;

import javax.swing.*;

import model.RegistrationException;
import model.User.User;
import java.awt.*;

import common.*;

//SimpleGui

public class KalenderFrame extends JFrame {
	
	// reference to the frame itself
	private final KalenderFrame thisSimpleFrame;
	
	// JMeunItem to exit the application 
	private final JMenuItem exitItem;
	private final JMenuItem changeUser;
	private final JMenuItem register;
	private final JMenuItem logOut;

	/**
	 * constructor
	 * @throws UnsupportedLookAndFeelException 
	 * @throws ListenerSetException 
	 */
	public KalenderFrame(User user) throws UnsupportedLookAndFeelException {
		// reference to this
		thisSimpleFrame=this;
	
		
		// set look and feels
		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		
		
		// Titelzeile
		setTitle("Project Imero");
		// Grösse des Frames
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.50), (int)(height*0.50));
		
		//*****************************************************************************
		// Menu
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		menuBar.add(fileMenu);
		changeUser = new JMenuItem("Change user");
		register = new JMenuItem("Register");
		logOut=new JMenuItem("Log out");
		exitItem=new JMenuItem("Exit");
		fileMenu.add(changeUser);
		fileMenu.add(register);
		fileMenu.add(logOut);
		fileMenu.add(exitItem);
		// add menu to the frame
		setJMenuBar(menuBar);
		//*****************************************************************************	

		// panel for gui components
		KalenderPanel panel = null;
		try {
			panel = new KalenderPanel(this, user);
		} catch (ListenerSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// add SimplePanel to the frame
		add(panel);

		// Groessenaenderung
	    this.setResizable(false);

		// center the frame
		setLocationRelativeTo(this);				

		// sichtbar machen
		setVisible(true); 
		
		// add action listeners
		addActionListeners(panel, user);

		assignIcon("./projectImero.jpg");
	}
	
	/**+
	 * @return the logOut
	 */
	public JMenuItem getLogOut() {
		return logOut;
	}
	
	/**
	 * @return the exitItem
	 */
	public JMenuItem getExitItem() {
		return exitItem;
	}

	/**
	 * @return the logIn
	 */
	public JMenuItem getLogIn() {
		return changeUser;
	}

	/**
	 * @return the register
	 */
	public JMenuItem getRegister() {
		return register;
	}

	/**
	 * 
	 */
	private void addActionListeners(KalenderPanel m, User user){
		// actionlistener exit application
		exitItem.addActionListener(new ApplicationActionListener(true, this));
		changeUser.addActionListener(new ApplicationActionListener(true, this));
		logOut.addActionListener(new ApplicationActionListener(true, this));
		register.addActionListener(new ApplicationActionListener(true, this));
	}
	
	/**
	 * 
	 * @param iconFile
	 */
	private void assignIcon(String iconFile) {
		Image img = getToolkit().getImage(iconFile);
		setIconImage(img);
	}
}
