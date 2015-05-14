package register;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

import common.HighLightMouseListener;
import common.ListenerSetException;
import common.RestrictedInsertTextField;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.awt.event.MouseListener;

/**
 * VATPanel
 * 
 *
 */
class RegisterPanel extends JPanel {

	// calculate btn
	private final JButton register_BTN;
	private final JButton cancel_BTN;

	// textfields changed from JTextField to RestrictInsertTextField
	private final RestrictedInsertTextField email_TF;
	private final RestrictedInsertTextField lastname_TF;
	private final RestrictedInsertTextField firstname_TF;
	private final RestrictedInsertTextField town_TF;
	private final RestrictedInsertTextField address_TF;
	private final JComboBox year_CB;
	private final JPasswordField password_JPF;
	private final JPasswordField repeat_JPF;
	// reference to the listener
	private final RegisterListener vatListener;
	private final HighLightMouseListener highlightMouseListener;

	// reference to the frame
	private final RegisterFrame vatFrame;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	@SuppressWarnings("unchecked")
	public RegisterPanel(RegisterFrame vatFrame, String fileAndPath) throws ListenerSetException {

		// reference to the frame
		this.vatFrame = vatFrame;

		// create listener object + reference to the panel as parameter
		vatListener = new RegisterListener(this, fileAndPath);
		highlightMouseListener = new HighLightMouseListener(new Color(0,191,255), false);

		// ****************************************************************
		// create JButton + text
		JPanel button_PNL = new JPanel();
		// close button
		register_BTN = new JButton("Register");
		// set the font
		register_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		//calculate_BTN.setEnabled(false);
		// activate button if euro and vat set
		register_BTN.addActionListener(vatListener);
		register_BTN.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Object source=arg0.getSource();
				// get the source component
				Component comp=(Component)source;				
				if (email_TF.getText().length()==0 || lastname_TF.getText().length()==0){
					comp.setEnabled(false);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Object source=arg0.getSource();
				Component comp=(Component)source;				
				comp.setEnabled(true);				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		button_PNL.add(register_BTN);
		
		cancel_BTN = new JButton("Cancel");
		cancel_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		cancel_BTN.addActionListener(vatListener);
		button_PNL.add(cancel_BTN);

		// anonymous textfield panel
		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		// GridLayout 2 rows and 2 columns
		textfieldPanel_PNL.setLayout(new GridLayout(8, 2));

		// create textfields
		//name_TF = new JTextField();
		email_TF=new RestrictedInsertTextField
				(RestrictedInsertTextField.FieldType.EMAIL, 3, 25);
		email_TF.setFont(new Font("Arial", Font.BOLD, 30));
		// copy paste disable
		email_TF.setTransferHandler(null);
		// name_TF.setPreferredSize((new Dimension(600,80)));
		email_TF.setHorizontalAlignment(JLabel.CENTER);
		lastname_TF = new RestrictedInsertTextField(RestrictedInsertTextField.FieldType.LETTERS, 3, 25);
		// copy paste disable
		lastname_TF.setTransferHandler(null);
		lastname_TF.setFont(new Font("Arial", Font.BOLD, 30));
		lastname_TF.setHorizontalAlignment(JLabel.CENTER);
		firstname_TF = new RestrictedInsertTextField(RestrictedInsertTextField.FieldType.LETTERS, 3, 25);
		// copy paste disable
		firstname_TF.setTransferHandler(null);
		firstname_TF.setFont(new Font("Arial", Font.BOLD, 30));
		firstname_TF.setHorizontalAlignment(JLabel.CENTER);
		address_TF = new RestrictedInsertTextField(RestrictedInsertTextField.FieldType.ALPHANUMERICALSPACE, 3, 25);
		// copy paste disable
		address_TF.setTransferHandler(null);
		address_TF.setFont(new Font("Arial", Font.BOLD, 30));
		address_TF.setHorizontalAlignment(JLabel.CENTER);
		town_TF = new RestrictedInsertTextField(RestrictedInsertTextField.FieldType.ALPHANUMERICALSPACE, 3, 25);
		// copy paste disable
		town_TF.setTransferHandler(null);
		town_TF.setFont(new Font("Arial", Font.BOLD, 30));
		town_TF.setHorizontalAlignment(JLabel.CENTER);
		password_JPF = new JPasswordField();
		// copy paste disable
		password_JPF.setTransferHandler(null);
		password_JPF.setFont(new Font("Arial", Font.BOLD, 30));
		password_JPF.setHorizontalAlignment(JLabel.CENTER);
		repeat_JPF = new JPasswordField();
		// copy paste disable
		repeat_JPF.setTransferHandler(null);
		repeat_JPF.setFont(new Font("Arial", Font.BOLD, 30));
		repeat_JPF.setHorizontalAlignment(JLabel.CENTER);
		year_CB = new JComboBox(vatListener.getInfo().getPossibleYears());
		
		
		
		
		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel("Email(will be used for login):"));
		textfieldPanel_PNL.add(email_TF);
		
		textfieldPanel_PNL.add(new JLabel("Lastname:"));
		textfieldPanel_PNL.add(lastname_TF);	
		
		textfieldPanel_PNL.add(new JLabel("Firstname:"));
		textfieldPanel_PNL.add(firstname_TF);
		
		textfieldPanel_PNL.add(new JLabel("Year of birth:"));
		textfieldPanel_PNL.add(year_CB);
		
		textfieldPanel_PNL.add(new JLabel("Town:"));
		textfieldPanel_PNL.add(town_TF);
		
		textfieldPanel_PNL.add(new JLabel("Address:"));
		textfieldPanel_PNL.add(address_TF);

		textfieldPanel_PNL.add(new JLabel("Password:"));
		textfieldPanel_PNL.add(password_JPF);
		textfieldPanel_PNL.add(new JLabel("Password:"));
		textfieldPanel_PNL.add(repeat_JPF);

		//****************************************************************************************************************************
		// add components
		// set background color
		// set layout manager of the panel
		this.setLayout(new BorderLayout());

		// to the north
		JLabel headline_LBL = new JLabel("Register user");
		headline_LBL.setHorizontalAlignment(JLabel.CENTER);
		headline_LBL.setFont(new Font("Arial", Font.BOLD, 30));
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
		register_BTN.addActionListener(vatListener);
		register_BTN.addMouseListener(new HighLightMouseListener(
				Color.GREEN, false));
		email_TF.addMouseListener(highlightMouseListener);
		lastname_TF.addMouseListener(highlightMouseListener);
		firstname_TF.addMouseListener(highlightMouseListener);
		year_CB.addMouseListener(highlightMouseListener);
		town_TF.addMouseListener(highlightMouseListener);
		address_TF.addMouseListener(highlightMouseListener);
		password_JPF.addMouseListener(highlightMouseListener);
		repeat_JPF.addMouseListener(highlightMouseListener);
	}

	/**
	 * @return the close_BTN
	 */
	public JButton getCalculate_BTN() {
		return register_BTN;
	}


	/**
	 * @return the name_TF
	 */
	public JTextField getEuro_TF() {
		return email_TF;
	}


	/**
	 * @return the town_TF
	 */
	public JTextField getVat_TF() {
		return lastname_TF;
	}


	/**
	 * @return the simpleFrame
	 */
	public RegisterFrame getVatFrame() {
		return vatFrame;
	}

	/**
	 * @return the register_BTN
	 */
	public JButton getRegister_BTN() {
		return register_BTN;
	}

	/**
	 * @return the cancel_BTN
	 */
	public JButton getCancel_BTN() {
		return cancel_BTN;
	}

	/**
	 * @return the repeat_JPF
	 */
	public JPasswordField getRepeat_JPF() {
		return repeat_JPF;
	}

	/**
	 * @return the email_TF
	 */
	public RestrictedInsertTextField getEmail_TF() {
		return email_TF;
	}

	/**
	 * @return the lastname_TF
	 */
	public RestrictedInsertTextField getLastname_TF() {
		return lastname_TF;
	}

	/**
	 * @return the firstname_TF
	 */
	public RestrictedInsertTextField getFirstname_TF() {
		return firstname_TF;
	}

	/**
	 * @return the town_TF
	 */
	public RestrictedInsertTextField getTown_TF() {
		return town_TF;
	}

	/**
	 * @return the address_TF
	 */
	public RestrictedInsertTextField getAddress_TF() {
		return address_TF;
	}

	/**
	 * @return the password_JPF
	 */
	public JPasswordField getPassword_JPF() {
		return password_JPF;
	}

	/**
	 * @return the year_CB
	 */
	public JComboBox getYear_CB() {
		return year_CB;
	}
	
	public void close() {
		vatFrame.dispose();
	}
}