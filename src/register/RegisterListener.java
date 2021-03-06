package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.User.User;
import model.User.Users;


/**
 * 
 * VATListener
 *
 */
public class RegisterListener implements ActionListener {

	// Reference to the graphical components
	private RegisterPanel registerPanel;
	private Users user;

	/**
	 * 
	 * @param p
	 */
	public RegisterListener(RegisterPanel p) {
		registerPanel = p;
		try {
			user = new Users();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the source of the component
		Object source = e.getSource();

		if (source == registerPanel.getCalculate_BTN()) {
			if(!registerPanel.getEmail_TF().getText().toString().contains("@"))
				JOptionPane.showMessageDialog(null, "Keine E-Mail "+registerPanel.getEmail_TF().getText().toString()+" password: "+registerPanel.getPassword_JPF().getPassword());
			else {
				String firstPw = new String(registerPanel.getPassword_JPF().getPassword());
				String secPw = new String(registerPanel.getRepeat_JPF().getPassword());
				if(firstPw.equals(secPw)) {
					try {
						String typedPass = new String(registerPanel.getPassword_JPF().getPassword());
						User a = getInfo().registrateUser(user, new User(registerPanel.getEmail_TF().getText(),registerPanel.getLastname_TF().getText(),
								registerPanel.getFirstname_TF().getText(), registerPanel.getAddress_TF().getText(), registerPanel.getTown_TF().getText(),
								Integer.parseInt(registerPanel.getYear_CB().getSelectedItem().toString()),typedPass, true));
						if(a == null){
							JOptionPane.showMessageDialog(null, "Email already exists!");
						}else{
							JOptionPane.showMessageDialog(null, "Registration successful!");
							registerPanel.close();
						}
					}
					catch (Exception e1) {
						if(registerPanel.getPassword_JPF().getPassword().length < 8) {
							JOptionPane.showMessageDialog(null, "Password is too short");
						} else {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "You could no registrate");
						}
					}


				} else
					try {
						JOptionPane.showMessageDialog(null, "The passwords are not the same");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "The passwords are not the same");
					}
			}
			
		}
		
		if(source == registerPanel.getCancel_BTN()) {
			registerPanel.close();
		}
	}
	
	public Users getInfo() {
		return user;
	}
}
