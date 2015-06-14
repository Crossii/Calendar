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


		JPanel button_PNL = new JPanel();

		JPanel textfieldPanel_PNL;
		textfieldPanel_PNL = new JPanel();
		// GridLayout 2 rows and 1 columns
		textfieldPanel_PNL.setLayout(new GridLayout(2, 1));
		
		JLabel text = new JLabel("<html><center>Please register or log in</center></html>");
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



		this.setLayout(new BorderLayout());

		// to the north
		JLabel headline_LBL = new JLabel("Your personal Calendar");
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


	public void close() {
		mainFrame.dispose();
	}
}