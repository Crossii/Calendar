package common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * A listener for restricted user input
 * 
 * @author Leo Fanzott
 *
 */
public class RestrictCharAndMaxLengthKeyListener implements KeyListener {

	// maxLength of InputChar
	private int maxLength;
	// regex to restrict character
	private String restriction;
	// the field who gets the listener
	private JTextField fieldToCheck_TF;

	/**
	 * Sets max-length and restrictions (regular expression) for the user input
	 * 
	 * @param maxLength
	 * @param restriction
	 * @throws ListenerSetException 
	 */
	public RestrictCharAndMaxLengthKeyListener(int maxLength, String restriction, JTextField fieldToCheck_TF) throws ListenerSetException {
		setMaxLength(maxLength);
		setRestriction(restriction);
		setFieldToCheck_TF(fieldToCheck_TF);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (fieldToCheck_TF.getText().length()>=maxLength) arg0.consume();
		// TODO Auto-generated method stub
		String str=fieldToCheck_TF.getText()+String.valueOf(arg0.getKeyChar());
		// check the input constraints given as regulat expression
		if (!str.matches(restriction)) arg0.consume();
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 * @throws ListenerSetException 
	 */
	public void setMaxLength(int maxLength) throws ListenerSetException {
		if (maxLength<=0) throw new ListenerSetException("Max length must not be <= 0");
		this.maxLength = maxLength;
	}


	/**
	 * @return the restriction
	 */
	public String getRestriction() {
		return restriction;
	}

	/**
	 * @param restriction
	 *            the restriction to set
	 * @throws ListenerSetException 
	 */
	public void setRestriction(String restriction) throws ListenerSetException {
		if (restriction==null) throw new ListenerSetException("Null for restriction is not allowed");
		this.restriction = restriction;
	}

	/**
	 * @return the fieldToCheck
	 */
	public JTextField getFieldToCheck_TF() {
		return fieldToCheck_TF;
	}

	/**
	 * @param fieldToCheck the fieldToCheck to set
	 * @throws ListenerSetException 
	 */
	public void setFieldToCheck_TF(JTextField fieldToCheck) throws ListenerSetException {
		if (fieldToCheck==null) throw new ListenerSetException("Null for fieldtocheck is not allowed");
		this.fieldToCheck_TF = fieldToCheck;
	}

}
