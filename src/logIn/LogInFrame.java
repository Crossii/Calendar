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
		// this.setUndecorated(true); // window without border and title
	
		
		// set look and feels
		UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel()); 
//		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel()); 
//		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel()); 
		
		
		// Titelzeile
		setTitle("Log in"); 
		// Grösse des Frames

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.40), (int)(height*0.25));
		//setPreferredSize(new Dimension(800,200));

		// panel for gui components
		LogInPanel panel = new LogInPanel(this, m);
		
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
