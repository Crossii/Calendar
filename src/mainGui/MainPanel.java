package mainGui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import common.ListenerSetException;

/**
 * VATPanel
 * 
 *
 */
public class MainPanel extends JPanel {
	// reference to the frame
	private final MainFrame mainFrame;
	
	private final MainListener mainListener;
	
	private final JButton logIn_BTN;
	private final JButton register_BTN;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	public MainPanel(MainFrame mainFrame) throws Exception {
		// reference to the frame
		

		// reference to the frame
		this.mainFrame = mainFrame;

		// create listener object + reference to the panel as parameter
		mainListener = new MainListener(this);

		// ****************************************************************
		// create JButton + text
		JPanel button_PNL = new JPanel();

		// anonymous textfield panel
		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		// GridLayout 2 rows and 2 columns
		textfieldPanel_PNL.setLayout(new GridLayout(2, 3));
		// create 
		
		JLabel text = new JLabel("<html>Bitte melden sie sich an <br>oder registrieren sie sich.</html>");
		text.setFont(new Font("Arial", Font.BOLD, 20));
		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel());
		textfieldPanel_PNL.add(text);
		textfieldPanel_PNL.add(new JLabel());
		
		
		// reset button
		logIn_BTN = new JButton("Login");
		// set the font
		logIn_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		// close button
		register_BTN = new JButton("Register");
		// set the font
		register_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		textfieldPanel_PNL.add(new JLabel());
		button_PNL.add(logIn_BTN);
		button_PNL.add(register_BTN);
		textfieldPanel_PNL.add(button_PNL);


		//****************************************************************************************************************************
		// add components
		// set background color
		// set layout manager of the panel
		this.setLayout(new BorderLayout());
		// set a random color for the background
		// textfieldPanel_PNL.setBackground(new Color(new Random().nextInt(256),
		// new Random().nextInt(256), new Random().nextInt(256)));

		// to the north
		JLabel headline_LBL = new JLabel("Dein eigener Kalender");
		headline_LBL.setFont(new Font("Arial", Font.BOLD, 30));
		headline_LBL.setHorizontalAlignment(JLabel.CENTER);
		headline_LBL.add(textfieldPanel_PNL);
		this.add(headline_LBL, BorderLayout.NORTH);
		// to the center
		this.add(textfieldPanel_PNL, BorderLayout.CENTER);
		
		addActionListeners();
	}
	
	
	/**
	 * @return the logIn_BTN
	 */
	public JButton getLogIn_BTN() {
		return logIn_BTN;
	}


	/**
	 * @return the register_BTN
	 */
	public JButton getRegister_BTN() {
		return register_BTN;
	}

	/**
	 * add actionListeners
	 * 
	 * @throws ListenerSetException
	 */
	private void addActionListeners() throws ListenerSetException {
		logIn_BTN.addActionListener(mainListener);
		register_BTN.addActionListener(mainListener);
	}


	/**
	 * @return the simpleFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	
	public void close() {
		mainFrame.dispose();
	}
}