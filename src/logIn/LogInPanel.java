package logIn;

import javax.swing.*;
import mainGui.MainPanel;
import common.*;
import common.RestrictedInsertTextField.FieldType;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	// reference to the frame
	private final LogInFrame logInFrame;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	public LogInPanel(LogInFrame simpleFrame, MainPanel m) throws ListenerSetException {

		// reference to the frame
		this.logInFrame = simpleFrame;

		// create listener object + reference to the panel as parameter
		simpleListener = new LogInListener(this, m);

		// ****************************************************************
		// create JButton + text
		JPanel button_PNL = new JPanel();
		// reset button
		logIn_BTN = new JButton("Login");
		logIn_BTN.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Object source = arg0.getSource();
				// get the source component
				Component comp = (Component) source;
				if (mail_TF.getText().length() == 0 || password_JPF.getText().length() == 0) {
					comp.setEnabled(false);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Object source = arg0.getSource();
				Component comp = (Component) source;
				comp.setEnabled(true);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		// set the font
		logIn_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		// close button
		close_BTN = new JButton("Cancel");
		// set the font
		close_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		button_PNL.add(logIn_BTN);
		button_PNL.add(close_BTN);

		JPanel textfieldPanel_PNL;
		textfieldPanel_PNL = new JPanel();
		// GridLayout 2 rows and 2 columns
		textfieldPanel_PNL.setLayout(new GridLayout(2, 2));
		// create textfields
		mail_TF = new RestrictedInsertTextField(FieldType.EMAIL, 3, 25);
		mail_TF.setColumns(2);
		password_JPF = new JPasswordField();
		// copy paste disable
		password_JPF.setTransferHandler(null);



		// add textfields to the panel

		textfieldPanel_PNL.add(new JLabel("Email"));
		textfieldPanel_PNL.add(mail_TF);

		textfieldPanel_PNL.add(new JLabel("Passwort:"));
		textfieldPanel_PNL.add(password_JPF);

		this.setLayout(new BorderLayout());

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
		close_BTN.addActionListener(simpleListener);
		password_JPF.addActionListener(simpleListener);
		password_JPF.addKeyListener(simpleListener);
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


	public void close() {
		logInFrame.dispose();
	}

}