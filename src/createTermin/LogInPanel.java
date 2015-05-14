package createTermin;

import java.awt.Color;

import javax.swing.*;

import mainGui.MainPanel;
import common.*;
import common.RestrictedInsertTextField.FieldType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * SimplePanel
 * 
 *
 */
class LogInPanel extends JPanel {

	// buttons
	private final JButton logIn_BTN;
	private final JButton close_BTN;

	// textfields
	private final RestrictedInsertTextField mail_TF;
	private final JPasswordField password_JPF;

	// reference to the listener
	private final LogInListener simpleListener;
	private final HighLightMouseListener highlightMouseListener;

	// reference to the frame
	private final LogInFrame logInFrame;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	public LogInPanel(LogInFrame simpleFrame, String fileAndPath, MainPanel m) throws ListenerSetException {

		// reference to the frame
		this.logInFrame = simpleFrame;

		// create listener object + reference to the panel as parameter
		simpleListener = new LogInListener(this, fileAndPath, m);
		highlightMouseListener = new HighLightMouseListener(new Color(0,191,255), false);

		// ****************************************************************
		// create JButton + text
		JPanel button_PNL = new JPanel();
		// reset button
		logIn_BTN = new JButton("Login");
		// set the font
		logIn_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		// close button
		close_BTN = new JButton("Cancel");
		// set the font
		close_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		button_PNL.add(logIn_BTN);
		button_PNL.add(close_BTN);

		// anonymous textfield panel
		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		// GridLayout 2 rows and 2 columns
		textfieldPanel_PNL.setLayout(new GridLayout(2, 2));
		// create textfields
		mail_TF = new RestrictedInsertTextField(FieldType.EMAIL, 3, 25);
		// name_TF.setPreferredSize((new Dimension(600,80)));
		password_JPF = new JPasswordField();
		// copy paste disable
		password_JPF.setTransferHandler(null);
		

		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel("Email"));
		textfieldPanel_PNL.add(mail_TF);

		textfieldPanel_PNL.add(new JLabel("Passwort:"));
		textfieldPanel_PNL.add(password_JPF);

		//****************************************************************************************************************************
		// add components
		// set background color
		// set layout manager of the panel
		this.setLayout(new BorderLayout());
		// set a random color for the background
		// textfieldPanel_PNL.setBackground(new Color(new Random().nextInt(256),
		// new Random().nextInt(256), new Random().nextInt(256)));

		// to the north
		JLabel headline_LBL = new JLabel("Person detail information");
		headline_LBL.setFont(new Font("Arial", Font.BOLD, 30));
		headline_LBL.setHorizontalAlignment(JLabel.CENTER);
		this.add(headline_LBL, BorderLayout.NORTH);
		// to the center
		this.add(textfieldPanel_PNL, BorderLayout.CENTER);
		// to the south
		this.add(button_PNL, BorderLayout.SOUTH);

		// add action listeners
		addActionListeners();
	}

	/**
	 * add actionListeners
	 * 
	 * @throws ListenerSetException
	 */
	private void addActionListeners() throws ListenerSetException {
		logIn_BTN.addActionListener(simpleListener);
		logIn_BTN.addMouseListener(highlightMouseListener);
		close_BTN.addActionListener(simpleListener);
		close_BTN.addMouseListener(highlightMouseListener);
		password_JPF.addActionListener(simpleListener);
		mail_TF.addActionListener(simpleListener);
	}

	/**
	 * @return the reset_BTN
	 */
	public JButton getReset_BTN() {
		return logIn_BTN;
	}


	/**
	 * @return the close_BTN
	 */
	public JButton getClose_BTN() {
		return close_BTN;
	}


	/**
	 * @return the mail_CB
	 */
	public RestrictedInsertTextField getMail_CB() {
		return mail_TF;
	}

	/**
	 * @return the password_JPF
	 */
	public JPasswordField getPassword_JPF() {
		return password_JPF;
	}

	/**
	 * @return the town_TF
	 */
	public JTextField getYearOfBirth_TF() {
		return password_JPF;
	}


	/**
	 * @return the simpleFrame
	 */
	public LogInFrame getSimpleFrame() {
		return logInFrame;
	}
	
	public void close() {
		logInFrame.dispose();
	}

}