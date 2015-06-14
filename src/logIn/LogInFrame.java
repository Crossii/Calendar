package logIn;


import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import mainGui.*;
import common.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * SimpleFrame
 * 
 *
 */
public class LogInFrame extends JFrame {
	
	// reference to the frame itself
	private final LogInFrame thisSimpleFrame;
	


	/**
	 * constructor
	 * @throws UnsupportedLookAndFeelException 
	 * @throws ListenerSetException 
	 */
	public LogInFrame(MainPanel m) throws UnsupportedLookAndFeelException, ListenerSetException {
		// reference to this
		thisSimpleFrame=this;
	
		
		// set look and feels
		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());

		
		// Title
		setTitle("Project Imero");

		// Size of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.40), (int)(height*0.25));

		// panel for gui components
		LogInPanel panel = new LogInPanel(this, m);
		
		// add SimplePanel to the frame
		add(panel);

		// The size is changeable
	    this.setResizable(true);

		// center the frame
		setLocationRelativeTo(this);				

		// makes it visible
		setVisible(true);

		// changes the icon to the file below
		assignIcon("./projectImero.jpg");
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
