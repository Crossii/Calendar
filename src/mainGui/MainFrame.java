package mainGui;


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
public class MainFrame extends JFrame {
	
	// reference to the frame itself
	private final MainFrame thisSimpleFrame;
	private final MainPanel main;
	
	// JMeunItem to exit the application 
	private final JMenuItem exitItem;
	private final JMenuItem logIn;
	private final JMenuItem register;

	/**
	 * constructor
	 * @throws UnsupportedLookAndFeelException 
	 * @throws ListenerSetException 
	 */
	public MainFrame() throws UnsupportedLookAndFeelException, ListenerSetException {
		// reference to this
		thisSimpleFrame=this;
	
		
		// set look and feels
//		UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel());
		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		
		
		// title
		setTitle("Project Imero");
		// size of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.30), (int)(height*0.50));
		
		//*****************************************************************************
		// Menu
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		menuBar.add(fileMenu);
		logIn = new JMenuItem("Log in");
		register = new JMenuItem("Register");
		exitItem=new JMenuItem("Exit");
		fileMenu.add(logIn);
		fileMenu.add(register);
		fileMenu.add(exitItem);
		// add menu to the frame
		setJMenuBar(menuBar);
		//*****************************************************************************	

		// panel for gui components
		MainPanel panel = null;
		try {
			panel = new MainPanel(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		main = panel;
		
		// add SimplePanel to the frame
		add(panel);

		// able to change the size
	    this.setResizable(true);

		// center the frame
		setLocationRelativeTo(this);				

		// makes it visible
		setVisible(true); 
		
		// add action listeners
		addActionListeners(panel);

		//changes the icon to the file below
		assignIcon("./projectImero.jpg");
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
	
	public MainPanel getMain() {
		return main;
	}

	/**
	 * 
	 */
	private void addActionListeners(MainPanel m){
		// actionlistener exit application
		exitItem.addActionListener(new ApplicationActionListener(false, this, m));
		logIn.addActionListener(new ApplicationActionListener(false, this, m));
		register.addActionListener(new ApplicationActionListener(false, this, m));
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
