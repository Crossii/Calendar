package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Benutzerdaten {
	private String file = "userData.csv";
	private  String id;
	private  String vorname;
	private  String nachname;
	private  String telefon;
	private  String adresse; 
	private  String postleitzahl;	//Ort integriert 
	
	
	
	public Benutzerdaten(String id, String vorname,
			String nachname, String telefon, String adresse, String postleitzahl) {
		super();
		this.id = id;
		this.vorname =vorname;
		this.nachname = nachname;
		this.telefon = telefon;
		this.adresse = adresse;
		this.postleitzahl = postleitzahl;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPostleitzahl() {
		return postleitzahl;
	}
	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}
	
	public void intoCsv() throws IOException{
		String csvString = id+";"+vorname+";"+nachname+";"+telefon+";"+adresse+";"+postleitzahl;
		FileWriter f = new FileWriter(file,true); 
		f.append(csvString+"\n");
		f.append(System.lineSeparator());
		f.close();
	}
	
	public static void main(String [ ] args) throws IOException{
		Benutzerdaten b = new Benutzerdaten("00001","Pavle","Pajic","0699123456789","Arndstrasse 21","1220 Wien");
		b.intoCsv();
	{

	
		}
	}
}
