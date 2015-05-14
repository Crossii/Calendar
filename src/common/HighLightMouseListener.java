package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * A MouseListener to highlight the active component and set the focus
 * @author Leo Fanzott
 *
 */
public class HighLightMouseListener implements MouseListener{
	
	// color which is used to highlight the component
	private Color highLightColor;
	// the last color of the component
	private Color lastColor;
	// set the focus
	private boolean setFocus;

	/**
	 * Set the background color of the component
	 * Should request-focus be invoked
	 * 
	 * @param color
	 * @param setFocus
	 */
	public HighLightMouseListener(Color color, boolean setFocus) {
		// TODO Auto-generated constructor stub
		this.highLightColor=color;
		this.setFocus=setFocus;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source=arg0.getSource();
		// get the source component
		Component comp=(Component)source;
		lastColor=comp.getBackground();
		comp.setBackground(highLightColor);
		if (setFocus) comp.requestFocus();
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source=arg0.getSource();
		Component comp=(Component)source;
		comp.setBackground(lastColor);		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
