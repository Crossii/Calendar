package kalenderGui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.JTableHeader;

import model.User.User;

import java.awt.*;

import common.*;
/**
 * SimplePanel
 *
 *
 */
public class KalenderPanel extends JPanel {

	// buttons
	private final JButton create_BTN;
	private final JButton update_BTN;
	private final JButton delete_BTN;

	private final JButton nextMonth_BTN;
	private final JButton currentMonth_BTN;
	private final JButton lastMonth_BTN;

	// textfields
	private final JTextField von_TF;
	private final JTextField bis_TF;
	private final JEditorPane beschreibung_TF;
	private final JTable kalender_T;

	private final Border raisedetched;

	private final JLabel month_LBL;

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
	public KalenderPanel(KalenderFrame simpleFrame,User user, String fileAndPathSchedules) throws ListenerSetException {
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		// reference to the frame
		this.simpleFrame = simpleFrame;

		// create listener object + reference to the panel as parameter
		simpleListener = new KalenderListener(this,user, fileAndPathSchedules);
		highlightMouseListener =
				new HighLightMouseListener(Color.RED, false);

		//*******************************************************


		//*******************************************************

		JPanel button_PNL = new JPanel();
		kalender_T = new JTable(simpleListener.getSchedules().getTable(), simpleListener.getSchedules().getTableHead());  //header in die JScrollPane
		kalender_T.setCellSelectionEnabled(true);
		kalender_T.setFont(new Font("Arial", Font.BOLD, 20));
		kalender_T.setBackground(Color.WHITE);
		kalender_T.setBorder(raisedetched);
		kalender_T.setRowHeight(72);

		//focuses the current day
		int column = simpleListener.getSchedules().getCurrentColumnDay();
		int row = simpleListener.getSchedules().getCurrentRowDay();
		System.out.println("Row: " + row);
		System.out.println("Column: " + column);
		kalender_T.setColumnSelectionInterval(column, column);
		kalender_T.setRowSelectionInterval(row, row);


		create_BTN = new JButton("Create");
		// set the font
		create_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		// close button
		update_BTN = new JButton("Update");
		// set the font
		update_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		delete_BTN = new JButton("Delete");
		// set the font
		delete_BTN.setFont(new Font("Arial", Font.BOLD, 30));
		button_PNL.add(kalender_T);
		button_PNL.setBorder(raisedetched);


		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		textfieldPanel_PNL.setBorder(raisedetched);
		textfieldPanel_PNL.setLayout(new GridLayout(7, 1));
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
		beschreibung_TF.setFont(new Font("Arial", Font.BOLD, 11));
		beschreibung_TF.setEnabled(false);

		lastMonth_BTN = new JButton("Last Month");
		nextMonth_BTN = new JButton("Next Month");
		currentMonth_BTN = new JButton("Current month");
		JPanel buttonLine_PNL = new JPanel();
		buttonLine_PNL.add(lastMonth_BTN);
		buttonLine_PNL.add(currentMonth_BTN);
		buttonLine_PNL.add(nextMonth_BTN);
		
		textfieldPanel_PNL.add(buttonLine_PNL);
		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel("Beginning:"));
		textfieldPanel_PNL.add(von_TF);

		textfieldPanel_PNL.add(new JLabel("Ending:"));
		textfieldPanel_PNL.add(bis_TF);
		textfieldPanel_PNL.add(new JLabel("Description:"));
		textfieldPanel_PNL.add(beschreibung_TF);

		JPanel crud_PNL = new JPanel();
		crud_PNL.setBorder(raisedetched);
		crud_PNL.setLayout(new GridLayout(1,3));
		crud_PNL.add(create_BTN);
		crud_PNL.add(update_BTN);
		crud_PNL.add(delete_BTN);

		//****************************************************************************************************************************
		// add components
		// set background color
		// set layout manager of the panel
		this.setLayout(new BorderLayout());
		// set a random color for the background
		// textfieldPanel_PNL.setBackground(new Color(new Random().nextInt(256),
		// new Random().nextInt(256), new Random().nextInt(256)));



		// to the north
		month_LBL = new JLabel(""+simpleListener.getSchedules().getCurrentMonth());
		JPanel headline_PNL = new JPanel();
		month_LBL.setFont(new Font("Arial", Font.BOLD, 30));
		month_LBL.setHorizontalAlignment(JLabel.CENTER);
		//headline_PNL.setLayout(new GridLayout(1, 2));
		JLabel headline_LBL = new JLabel("Person detail information in");
		headline_LBL.setFont(new Font("Arial", Font.BOLD, 30));
		headline_LBL.setHorizontalAlignment(JLabel.CENTER);
		headline_PNL.add(headline_LBL);
		headline_PNL.add(month_LBL);
		this.add(headline_PNL, BorderLayout.NORTH);
		// to the center
		this.add(textfieldPanel_PNL, BorderLayout.WEST);
		// to the south
		this.add(button_PNL, BorderLayout.CENTER);
		this.add(crud_PNL, BorderLayout.SOUTH);

		// add action listeners
		addActionListeners();
	}

	public JTable getKalender_T() {
		return kalender_T;
	}

	private void addActionListeners() throws ListenerSetException {
		create_BTN.addActionListener(simpleListener);
		update_BTN.addActionListener(simpleListener);
		lastMonth_BTN.addActionListener(simpleListener);
		currentMonth_BTN.addActionListener(simpleListener);
		nextMonth_BTN.addActionListener(simpleListener);
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
	public JButton getCreate_BTN() {
		return create_BTN;
	}


	/**
	 * @return the close_BTN
	 */
	public JButton getUpdate_BTN() {
		return update_BTN;
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

	public JButton getNextMonth_BTN() {
		return nextMonth_BTN;
	}

	public JButton getDelete_BTN() {
		return delete_BTN;
	}

	public JButton getCurrentMonth_BTN() {
		return currentMonth_BTN;
	}

	public JButton getLastMonth_BTN() {
		return lastMonth_BTN;
	}

	public JEditorPane getBeschreibung_TF() {
		return beschreibung_TF;
	}

	public JLabel getMonth_LBL() {
		return month_LBL;
	}
}
