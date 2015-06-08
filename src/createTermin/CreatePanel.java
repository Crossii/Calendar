package createTermin;

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
public class CreatePanel extends JPanel {
	// reference to the frame
	private final CreateFrame mainFrame;
	
	private final CreateListener mainListener;
	
	private final JComboBox logIn_BTN;
	private final JComboBox register_BTN;
	private final JLabel beginningLBL;
	private final JLabel endingLBL;
	private final JEditorPane description_EP;
	private final JLabel description_LBL;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	public CreatePanel(CreateFrame mainFrame) throws ListenerSetException {
		// reference to the frame
		

		// reference to the frame
		this.mainFrame = mainFrame;

		// create listener object + reference to the panel as parameter
		mainListener = new CreateListener(this);
		JPanel button_PNL = new JPanel();
		JPanel textfieldPanel_PNL;
		textfieldPanel_PNL = new JPanel();
		textfieldPanel_PNL.setLayout(new GridLayout(2, 2));
		button_PNL.setLayout(new GridLayout(3,2));
		
		
		// reset button
		beginningLBL = new JLabel("Beginning");
		beginningLBL.setFont(new Font("Arial", Font.BOLD, 30));
		logIn_BTN = new JComboBox();
		logIn_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		endingLBL = new JLabel("Ending");
		endingLBL.setFont(new Font("Arial", Font.BOLD, 30));
		register_BTN = new JComboBox();
		register_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		description_LBL = new JLabel("Description:");
		description_LBL.setFont(new Font("Arial", Font.BOLD,30));
		description_EP = new JEditorPane();
		description_EP.setFont(new Font("Arial", Font.BOLD, 11));
		textfieldPanel_PNL.add(new JLabel());
		button_PNL.add(beginningLBL);
		button_PNL.add(logIn_BTN);
		button_PNL.add(endingLBL);
		button_PNL.add(register_BTN);
		button_PNL.add(description_LBL);
		button_PNL.add(description_EP);
		
		this.setLayout(new BorderLayout());

		JLabel headline_LBL = new JLabel("Dein eigener Kalender");
		
		headline_LBL.setFont(new Font("Arial", Font.BOLD, 30));
		headline_LBL.add(textfieldPanel_PNL);
		headline_LBL.setHorizontalAlignment(JLabel.CENTER);
		this.add(headline_LBL, BorderLayout.NORTH);
		// to the center
		this.add(button_PNL, BorderLayout.CENTER);
		
		addActionListeners();
	}

	private void addActionListeners() throws ListenerSetException {

	}


	/**
	 * @return the simpleFrame
	 */
	public CreateFrame getMainFrame() {
		return mainFrame;
	}
	
	public void close() {
		mainFrame.dispose();
	}
}