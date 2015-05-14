package common;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * 
 * @author Leo Fanzott
 *
 */
public class RestrictedInsertTextField 
         extends JTextField implements FocusListener {
	
	public enum FieldType{EMAIL,
		                  ALPHANUMERICALSPACE,
		                  DEFAULT,
		                  LETTERS,
		                  NUMBERS}
	
	private int minLength;
	private int maxLength;
	
	/**
	 * 
	 * @param fieldType
	 * @param minLength
	 * @param maxLength
	 * @throws ListenerSetException
	 */
	public RestrictedInsertTextField
	      (FieldType fieldType, int minLength, int maxLength) throws ListenerSetException{
		String restriction="";
		switch (fieldType){
		    // any sequence of numbers
			case EMAIL: restriction = "[a-z]+?[0-9]*\\.?[a-z]*\\@?[a-z]*\\.?[a-z]*"; break;
			case ALPHANUMERICALSPACE: restriction = "[a-zA-Z0-9 ]+"; break;
			case DEFAULT: restriction = ".*"; break;
			case LETTERS: restriction = "[a-z A-Z]+"; break;
			case NUMBERS: restriction = "[0-9]+"; break;
			
		}//switch
		setMaxLength(maxLength);
		setMinLength(minLength);
		// add the key listener
		this.addKeyListener
		(new RestrictCharAndMaxLengthKeyListener(maxLength,restriction,this));
		// add the focus listener
		this.addFocusListener(this);
	}// constructor

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if (this.getText().length()==0) return;
		if (!(this.getText().length()>=minLength&&
			  this.getText().length()<=maxLength)){
			JOptionPane.showMessageDialog(this, "Length must be between "+minLength+" and "+maxLength );
			this.requestFocus();
		}
		
	}

	/**
	 * @param minLength the minLength to set
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	

}
