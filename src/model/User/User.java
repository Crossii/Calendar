package model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Schedule.Schedules;
import model.User.Encoding.EncodingType;
import model.EncodingException;
import model.RegistrationException;

/**
 * 
 * @author Leo Fanzott
 *
 */
public class User implements Comparable<User>{
	
	// attributes
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private String celNumber;	
	private int yearOfBirth;
	private String password;
	
	/**
	 * @param email
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param celNumber
	 * @param yearOfBirth
	 * @throws RegistrationException
	 * @throws EncodingException
	 * @throws NoSuchAlgorithmException 
	 */
	public User(String email, String firstname, String lastname,
			String address, String celNumber, int yearOfBirth, String password, boolean encrypt) throws RegistrationException, EncodingException, NoSuchAlgorithmException {
		this.setEmail(email);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAddress(address);
		this.setCelNumber(celNumber);
		this.setYearOfBirth(yearOfBirth);
		if(encrypt)	this.encryptPassword(password);
		else this.setPassword(password);
	}

	/**
	 * @param email
	 * @throws RegistrationException 
	 */
	public User(String email) throws RegistrationException {
		this.setEmail(email);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 * @throws RegistrationException 
	 */
	public void setEmail(String email) throws RegistrationException {
		if (!email.matches("[a-z]+?[0-9]*\\.?[a-z]*\\@[a-z]+\\.[a-z]+")){
			throw new RegistrationException("Login must be a correct email address!");
		}

		this.email = email;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 * @throws RegistrationException 
	 */
	public void setFirstname(String firstname) throws RegistrationException {
		if (firstname==null || firstname.length()<3) 			
			throw new RegistrationException("Firstname must not be null or firstname.length < 3!");

		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 * @throws RegistrationException 
	 */
	public void setLastname(String lastname) throws RegistrationException {
		if (lastname==null || lastname.length()<3) 			
			throw new RegistrationException("Lastname must not be null or lastname.length < 3!");
		this.lastname = lastname;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 * @throws RegistrationException 
	 */
	public void setAddress(String address) throws RegistrationException {
		if (address==null || address.length()<3) 			
			throw new RegistrationException("Address has to be not null and address.length >= 3");
		this.address = address;
	}
	/**
	 * @return the town
	 */
	public String getTown() {
		return celNumber;
	}
	/**
	 * @param celNumber the town to set
	 * @throws RegistrationException 
	 */
	public void setCelNumber(String celNumber) throws RegistrationException {
		if (celNumber==null || celNumber.length()<3) 			
			throw new RegistrationException("Your number has to be not null and town.length >= 3!");
		this.celNumber = celNumber;
	}
	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	/**
	 * @param yearOfBirth the yearOfBirth to set
	 * @throws RegistrationException 
	 */
	public void setYearOfBirth(int yearOfBirth) throws RegistrationException {
		GregorianCalendar gc=new GregorianCalendar();
		int actualYear=gc.get(GregorianCalendar.YEAR);
		
		if (!(yearOfBirth>=1920 && yearOfBirth<=actualYear))
			throw new RegistrationException("Year of birth has to be >=1920 and <= "+actualYear);

		this.yearOfBirth = yearOfBirth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Password is saved as a MD5 hashcode
	 * @param password the password to set
	 * @throws RegistrationException 
	 * @throws EncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void encryptPassword(String password) throws RegistrationException, NoSuchAlgorithmException, EncodingException {
		if (password==null || password.length()<8)
			throw new RegistrationException("Password has to be not null and password.length >= 8");			
		// compute and save the hashcode of the password string
		Encoding encode = new Encoding(password, EncodingType.MD5);
		this.password = encode.getHashcodeAsString();
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return email + ";" + firstname + ";" + lastname + ";" + address + ";" + celNumber + ";" + yearOfBirth + ";" + getPassword() +";";
	}

	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return this.email.compareTo(arg0.getEmail());
	}


}
