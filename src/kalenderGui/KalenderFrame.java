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
	public KalenderFrame(User user, String fileAndPathUser, String fileAndPathSchedules) throws UnsupportedLookAndFeelException {
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
		setSize((int)(width*0.50), (int)(height*0.50));
		//setPreferredSize(new Dimension(800,200));
		
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
			panel = new KalenderPanel(this, user, fileAndPathSchedules);
		} catch (ListenerSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		addActionListeners(panel, user, fileAndPathUser);


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
	private void addActionListeners(KalenderPanel m, User user, String fileAndPath){
		// actionlistener exit application
		exitItem.addActionListener(new KalenderListener(this, user, fileAndPath));
		changeUser.addActionListener(new KalenderListener(this, user, fileAndPath));
		logOut.addActionListener(new KalenderListener(this, user, fileAndPath));
		register.addActionListener(new KalenderListener(this, user, fileAndPath));
	}
	
	/**
	 * 
	 * @param iconFile
	 */
	private void assignIcon(String iconFile) {
		Image img = getToolkit().getImage(iconFile);
		setIconImage(img);
	}

	public static void main(String[] args){
		UIManager.LookAndFeelInfo laf[] = UIManager
				.getInstalledLookAndFeels();
		for (int i = 0, n = laf.length; i < n; i++) {
			System.out.print("LAF Name: " + laf[i].getName() + "\t");
			System.out.println("  LAF Class name: "
					+ laf[i].getClassName());
		}

		if(args.length==1) {
			JOptionPane.showMessageDialog(null, "One file is missing");
			System.exit(1);
		}

		final String  fileAndPathUser = args[0];
		System.out.println(fileAndPathUser);
		final String  fileAndPathSchedules = args[1];
		System.out.println(fileAndPathSchedules);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				KalenderFrame frame = null;
				try {
					frame = new KalenderFrame(new User("coool@cool.at"), fileAndPathUser, fileAndPathSchedules);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (RegistrationException e) {
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
