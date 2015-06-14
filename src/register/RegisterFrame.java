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

		// the title
		setTitle("Project Imero");
		// size of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int)(width*0.50), (int)(height*0.50));

		// panel for gui components
		RegisterPanel panel = new RegisterPanel(this);
		
		// add SimplePanel to the frame
		add(panel);

		// able to change the size
	    this.setResizable(true);

		// center the frame
		setLocationRelativeTo(this);				

		// makes it visible
		setVisible(true);

		//changes the icon to the file below
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
