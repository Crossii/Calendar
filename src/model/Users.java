package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import model.Encoding.EncodingType;

/**
 * 
 * @author Leo Fanzott
 *
 *
 */
public class Users {

	// hold all exchange rates
	private ArrayList<User> users;
	
	// holds the name of the file
	private String fileAndPath;
	private boolean login;
	
	
	/**
	 * Create a model object
	 */
	public Users(String fileAndPath) {
		super();
		users=new ArrayList<User>();
		// load currencies
		loadUsers(fileAndPath);
		this.fileAndPath=fileAndPath;
	}
	
	/**
	 * @return the login
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(boolean login) {
		this.login = login;
	}

	/**
	 * get possible years from 1920 to the actual year
	 * @return
	 */
	public String[] getPossibleYears(){
		GregorianCalendar gc=new GregorianCalendar();
		int actualYear=gc.get(GregorianCalendar.YEAR);
		
		int numberYears=actualYear-1920;
		int startYear=1920;		
		String[] possibleYears=new String[numberYears+1];
		for (int i=0; i<possibleYears.length;i++){
			possibleYears[i]=""+(startYear+i);
		}		
		return possibleYears;
	}
	

	/**
	 * Load all user from a given file
	 * 
	 * @param fileAndPath
	 * @throws  
	 * @throws NoSuchAlgorithmException 
	 */
	private void loadUsers(String fileAndPath){
		try {
			BufferedReader br=new BufferedReader(new FileReader(fileAndPath));
			String line=br.readLine();
			while(line!=null){
				// split the string into components
				String[] sa=line.split(";");
				try{
					// create a user object
					if(sa == null) 
						return;
				   User user=new User(sa[0],sa[1],sa[2],sa[3],sa[4],Integer.parseInt(sa[5]),sa[6],false);
				   // add user to the collection
				   users.add(user);
				}catch (NumberFormatException | RegistrationException | NoSuchAlgorithmException | EncodingException nfe){
					System.out.println("File is invalid!");
					System.out.println(nfe.getMessage());
					System.exit(1);
				}
				line=br.readLine();			
			}//while
			sortUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("User file not found: "+fileAndPath);
			System.exit(1);
		}
		
	}
	
	
	/**
	 * @throws IOException 
	 * Registrate the user. User will be saved into the registration file users.csv
	 * @param user
	 * @return
	 * @throws  
	 */
	public User registrateUser(Users users, User user) throws IOException{
		for(User u : users.getUsers()) {
			if(u.equals(users)) 
				return null;
		}
		BufferedWriter out = new BufferedWriter(new BufferedWriter(new FileWriter(fileAndPath)));
		for(User u : users.getUsers()) {
			out.write(u.toString());
			out.newLine();
		}
		out.write(user.toString());
		out.close();
		return user;
	}
	

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws RegistrationException 
	 * @throws EncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public User login(String email, char[] password) {
		String passwordAsString = new String(password);
		User user = null;
		Encoding typedPass = null;
		try {
			typedPass = new Encoding(passwordAsString ,EncodingType.MD5);
		} catch (NoSuchAlgorithmException | EncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(email);
		passwordAsString = typedPass.getHashcodeAsString();
		for(User u:users) {
			if(u.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Users passwort: "+u.getPassword()+", E-Mail: "+u.getEmail());
				System.out.println("Eingegeben: "+passwordAsString+", E-Mail: "+email);
				if(u.getPassword().equals(passwordAsString)) { 
					System.out.println("Richtig!");
					try {
						user = users.get(users.indexOf(new User(email)));
						setLogin(true);
					} catch (RegistrationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return user;
	}
	
	/**
	 * sort the users
	 */
	private void sortUsers(){
		Collections.sort(users);
	}
	
	public String getFileAndPath() {
		return fileAndPath;
	}
	
	public void logOut() {
		setLogin(false);
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length==0){
			System.out.println("filename missed -> check the program arguments");
			System.exit(1);
		}
				
		// TODO Auto-generated method stub
	}

}
