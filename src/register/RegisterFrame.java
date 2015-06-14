package register;


import javax.swing.*;

import common.ListenerSetException;

import java.awt.*;

/**
 * SimpleFrame
 * 
 *
 */
public class RegisterFrame extends JFrame {
	
	// reference to the frame itself
	private final RegisterFrame thisSimpleFrame;
	

	/**
	 * constructor
	 * @throws UnsupportedLookAndFeelException 
	 * @throws ListenerSetException 
	 */
	public RegisterFrame() throws UnsupportedLookAndFeelException, ListenerSetException {
		// reference to this
		thisSimpleFrame=this;	


		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());

		
		// Titelzeile
		setTitle("Project Imero");
		// Grösse des Frames
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.50), (int)(height*0.50));

		// panel for gui components
		RegisterPanel panel = new RegisterPanel(this);
		
		// add SimplePanel to the frame
		add(panel);

		// Groessenaenderung
	    this.setResizable(false);

		// center the frame
		setLocationRelativeTo(this);				

		// sichtbar machen
		setVisible(true); 
		
		// add action listeners
		addActionListeners();

	}
	
	/**
	 * 
	 */
	private void addActionListeners(){
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
