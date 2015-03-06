package main.java.com.auphelia.models;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Contact {

	private String prenom ;
	private String nom ;
	private String rue;
	private String ville ;
	private String province ;
	private String codePostal ;
	
	public Contact(){}
	
	public Contact(String firstName, String lastName, String street,
			String city, String province, String postalCode) {
		this.prenom = firstName;
		this.nom = lastName;
		this.rue = street;
		this.ville = city;
		this.province = province;
		this.codePostal = postalCode;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
}
