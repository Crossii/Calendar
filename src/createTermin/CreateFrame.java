package createTermin;


import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import common.*;

/**
 * SimpleFrame
 * 
 *
 */
public class CreateFrame extends JFrame {
	
	// reference to the frame itself
	private final CreateFrame thisSimpleFrame;
	private final CreatePanel main;
	
	// JMeunItem to exit the application 
	private final JMenuItem exitItem;
	private final JMenuItem logIn;
	private final JMenuItem register;
	private final JMenuItem logOut;

	/**
	 * constructor
	 * @throws UnsupportedLookAndFeelException 
	 * @throws ListenerSetException 
	 */
	public CreateFrame(String fileAndPath) throws UnsupportedLookAndFeelException, ListenerSetException { 
		// reference to this
		thisSimpleFrame=this;	
		// this.setUndecorated(true); // window without border and title
	
		
		// set look and feels
		UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel()); 
//		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel()); 
		
		
		// Titelzeile
		setTitle("Register - LogIn"); 
		// Grösse des Frames
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.50), (int)(height*0.40)); 
		//setPreferredSize(new Dimension(800,200));
		
		//*****************************************************************************
		// Menu
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		menuBar.add(fileMenu);
		logIn = new JMenuItem("Log in");
		register = new JMenuItem("Register");
		logOut=new JMenuItem("Log out");
		exitItem=new JMenuItem("Exit");
		fileMenu.add(logIn);
		fileMenu.add(register);
		fileMenu.add(logOut);
		fileMenu.add(exitItem);
		// add menu to the frame
		setJMenuBar(menuBar);
		//*****************************************************************************	

		// panel for gui components
		CreatePanel panel = new CreatePanel(this, fileAndPath);
		main = panel;
		
		// add SimplePanel to the frame
		add(panel);

		// Groessenaenderung
	    this.setResizable(false);
		
		// packs the components
		// pack();

		// Fenster am Desktop zentrieren
//		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (int) ((d.getWidth() / 2 - this.getWidth() / 2));
//		int y = (int) ((d.getHeight() / 2 - this.getHeight() / 2));
//		setLocation(x, y); // Positionieren

		// center the frame
		setLocationRelativeTo(this);				

		// sichtbar machen
		setVisible(true); 
		
		// add action listeners
		addActionListeners(fileAndPath, panel);


	}
	
	/**
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
		return logIn;
	}

	/**
	 * @return the register
	 */
	public JMenuItem getRegister() {
		return register;
	}
	
	public CreatePanel getMain() {
		return main;
	}

	/**
	 * 
	 */
	private void addActionListeners(String fileAndPath, CreatePanel m){
		// actionlistener exit application
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
