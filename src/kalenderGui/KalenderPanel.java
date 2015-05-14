package kalenderGui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.User;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import common.*;
/**
 * SimplePanel
 * 
 *
 */
public class KalenderPanel extends JPanel {

	// buttons
	private final JButton reset_BTN;
	private final JButton close_BTN;

	// textfields
	private final JTextField von_TF;
	private final JTextField bis_TF;
	private final JEditorPane beschreibung_TF; 
	private final JTable kalender_T;
	
	private final Border raisedetched;

	// reference to the listener
	private final KalenderListener simpleListener;
	private final HighLightMouseListener highlightMouseListener;

	// reference to the frame
	private final KalenderFrame simpleFrame;

	/**
	 * constructor
	 * 
	 * @throws ListenerSetException
	 */
	public KalenderPanel(KalenderFrame simpleFrame,User user) throws ListenerSetException {
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		// reference to the frame
		this.simpleFrame = simpleFrame;

		// create listener object + reference to the panel as parameter
		simpleListener = new KalenderListener(this,user);
		highlightMouseListener = 
				new HighLightMouseListener(Color.RED, false);
		
		//*******************************************************
		

		//*******************************************************

		JPanel button_PNL = new JPanel();
		kalender_T = new JTable(7,7);
		kalender_T.setCellSelectionEnabled(true);
		kalender_T.setFont(new Font("Arial", Font.BOLD, 20));
		kalender_T.setBackground(Color.WHITE);
		kalender_T.setBorder(raisedetched);

		kalender_T.setRowHeight(72);
		

		reset_BTN = new JButton("Reset");
		// set the font
		reset_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		// close button
		close_BTN = new JButton("Close");
		// set the font
		close_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		button_PNL.add(kalender_T);
		button_PNL.setBorder(raisedetched);
		
		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		textfieldPanel_PNL.setBorder(raisedetched);
		textfieldPanel_PNL.setLayout(new GridLayout(3, 2));
		textfieldPanel_PNL.setPreferredSize(new Dimension(300,1));

		// create textfields
		von_TF = new JTextField(user.getFirstname()+" "+user.getLastname());
		von_TF.setFont(new Font("Arial", Font.BOLD, 30));
		von_TF.setHorizontalAlignment(JLabel.CENTER);
		von_TF.setEnabled(false);
		bis_TF = new JTextField(user.getYearOfBirth()+"");
		bis_TF.setFont(new Font("Arial", Font.BOLD, 30));
		bis_TF.setHorizontalAlignment(JLabel.CENTER);
		bis_TF.setEnabled(false);
		beschreibung_TF = new JTextPane();
		beschreibung_TF.setFont(new Font("Arial", Font.BOLD, 30));
		beschreibung_TF.setEnabled(false);

		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel("Name:"));
		textfieldPanel_PNL.add(von_TF);

		textfieldPanel_PNL.add(new JLabel("Year of birth:"));
		textfieldPanel_PNL.add(bis_TF);
		textfieldPanel_PNL.add(beschreibung_TF);

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
		this.add(textfieldPanel_PNL, BorderLayout.WEST);
		// to the south
		this.add(button_PNL, BorderLayout.CENTER);

		// add action listeners
		addActionListeners();
	}

	public JTable getKalender_T() {
		return kalender_T;
	}

	private void addActionListeners() throws ListenerSetException {
		reset_BTN.addActionListener(simpleListener);
		reset_BTN.addMouseListener(highlightMouseListener);
		close_BTN.addActionListener(simpleListener);
		close_BTN.addMouseListener(highlightMouseListener);
		bis_TF.addMouseListener(new HighLightMouseListener(
				Color.yellow, true));
		bis_TF.addKeyListener(
				new RestrictCharAndMaxLengthKeyListener(
				4, "[0-9]", bis_TF));
		von_TF.addMouseListener(new HighLightMouseListener(Color.yellow, true));
		von_TF.addKeyListener(new RestrictCharAndMaxLengthKeyListener(5,
				"[A-Z]", von_TF));
	}

	/**
	 * @return the reset_BTN
	 */
	public JButton getReset_BTN() {
		return reset_BTN;
	}


	/**
	 * @return the close_BTN
	 */
	public JButton getClose_BTN() {
		return close_BTN;
	}


	/**
	 * @return the name_TF
	 */
	public JTextField getVon_TF() {
		return von_TF;
	}


	/**
	 * @return the town_TF
	 */
	public JTextField getBis_TF() {
		return bis_TF;
	}


	/**
	 * @return the simpleFrame
	 */
	public KalenderFrame getSimpleFrame() {
		return simpleFrame;
	}

}
