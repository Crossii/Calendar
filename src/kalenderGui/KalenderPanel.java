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

	private final JButton setBeginning_BTN;
	private final JButton setEnding_BTN;

	// textfields
	private final JTextField von_TF;
	private final JTextField bis_TF;
	private final JEditorPane beschreibung_LBL;
	private final JTable kalender_T;
	private final JScrollPane calendar_SP;

	private final Border raisedetched;

	private final JLabel month_LBL;

	// reference to the listener
	private final KalenderListener simpleListener;

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

		//*******************************************************


		//*******************************************************

		JPanel button_PNL = new JPanel();
		kalender_T = new JTable(simpleListener.getSchedules().getTable(), simpleListener.getSchedules().getTableHead());  //header in die JScrollPane
		kalender_T.setCellSelectionEnabled(true);
		kalender_T.setFont(new Font("Arial", Font.BOLD, 20));
		kalender_T.setBackground(Color.WHITE);
		kalender_T.setBorder(raisedetched);
		kalender_T.setRowHeight(60);

		kalender_T.setDefaultRenderer(Object.class, new CostumRenderer(simpleListener.getSchedules().getCurrentDayPosition()[1], simpleListener.getSchedules().getCurrentDayPosition()[0], true));



		//kalender_T.getColumnModel().getColumn(columnIndex).setCellRenderer(

		//focuses the current day
		int column = simpleListener.getSchedules().getCurrentDayPosition()[0];
		int row = simpleListener.getSchedules().getCurrentDayPosition()[1];
		kalender_T.setColumnSelectionInterval(column, column);
		kalender_T.setRowSelectionInterval(row, row);
		kalender_T.getTableHeader().setReorderingAllowed(false);


		calendar_SP = new JScrollPane(kalender_T);


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
		button_PNL.add(calendar_SP);
		button_PNL.setBorder(raisedetched);


		JPanel textfieldPanel_PNL;
		// panel in the center
		textfieldPanel_PNL = new JPanel();
		textfieldPanel_PNL.setBorder(raisedetched);
		textfieldPanel_PNL.setLayout(new GridLayout(7, 1));
		textfieldPanel_PNL.setPreferredSize(new Dimension(300, 1));

		// create textfields
		von_TF = new JTextField("None");
		von_TF.setFont(new Font("Arial", Font.BOLD, 20));
		von_TF.setHorizontalAlignment(JLabel.CENTER);
		von_TF.setEnabled(false);
		von_TF.setPreferredSize(new Dimension(200, 50));

		setBeginning_BTN = new JButton("Set");
		setBeginning_BTN.setEnabled(false);

		bis_TF = new JTextField("None");
		bis_TF.setFont(new Font("Arial", Font.BOLD, 20));
		bis_TF.setHorizontalAlignment(JLabel.CENTER);
		bis_TF.setEnabled(false);
		bis_TF.setPreferredSize(new Dimension(200, 50));

		setEnding_BTN = new JButton("Set");
		setEnding_BTN.setEnabled(false);

		JPanel beginning_PNL = new JPanel(new FlowLayout());
		beginning_PNL.add(von_TF);
		beginning_PNL.add(setBeginning_BTN);
		JPanel ending_PNL = new JPanel(new FlowLayout());
		ending_PNL.add(bis_TF);
		ending_PNL.add(setEnding_BTN);

		beschreibung_LBL = new JEditorPane("Nichts", "aspdkkasdpokasdpooaksdpoakwdpoaksd apsodkapsodkas dpoaksdpoaksd poaskd poaksd poaksd poaksdpoasd "); //"Day of week: "+simpleListener.getSchedules().getActualDayOfWeek()
		beschreibung_LBL.setFont(new Font("Arial", Font.BOLD, 12));
		beschreibung_LBL.setEnabled(false);

		lastMonth_BTN = new JButton("Last Month");
		lastMonth_BTN.setMargin(new Insets(0, 0, 0, 0));
		nextMonth_BTN = new JButton("Next Month");
		nextMonth_BTN.setMargin(new Insets(0, 0, 0, 0));
		currentMonth_BTN = new JButton("Current month");
		currentMonth_BTN.setMargin(new Insets(0, 0, 0, 0));
		JPanel buttonLine_PNL = new JPanel();
		buttonLine_PNL.setLayout(new FlowLayout());
		buttonLine_PNL.add(lastMonth_BTN);
		buttonLine_PNL.add(currentMonth_BTN);
		buttonLine_PNL.add(nextMonth_BTN);
		
		textfieldPanel_PNL.add(buttonLine_PNL);
		// add textfields to the panel
		textfieldPanel_PNL.add(new JLabel("Beginning:"));
		textfieldPanel_PNL.add(beginning_PNL);

		textfieldPanel_PNL.add(new JLabel("Ending:"));
		textfieldPanel_PNL.add(ending_PNL);
		textfieldPanel_PNL.add(new JLabel("Description:"));
		textfieldPanel_PNL.add(beschreibung_LBL);

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
		month_LBL = new JLabel(simpleListener.getSchedules().getCurrentMonth()+", "+simpleListener.getSchedules().getYear());
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
		//kalender_T.addMouseListener(new HighLightMouseListener(Color.RED, false));
		kalender_T.addMouseListener(new TableMouseListener(kalender_T));
		create_BTN.addActionListener(simpleListener);
		update_BTN.addActionListener(simpleListener);
		delete_BTN.addActionListener(simpleListener);
		lastMonth_BTN.addActionListener(simpleListener);
		currentMonth_BTN.addActionListener(simpleListener);
		nextMonth_BTN.addActionListener(simpleListener);
		setBeginning_BTN.addActionListener(simpleListener);
		setEnding_BTN.addActionListener(simpleListener);
		bis_TF.addKeyListener(new RestrictCharAndMaxLengthKeyListener(4, "[0-9]", bis_TF));
		von_TF.addKeyListener(new RestrictCharAndMaxLengthKeyListener(5, "[A-Z]", von_TF));
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

	public JEditorPane getBeschreibung_LBL() {
		return beschreibung_LBL;
	}

	public JLabel getMonth_LBL() {
		return month_LBL;
	}

	public JButton getSetBeginning_BTN() {
		return setBeginning_BTN;
	}

	public JButton getSetEnding_BTN() {
		return setEnding_BTN;
	}
}
