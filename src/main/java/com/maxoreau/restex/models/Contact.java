package com.maxoreau.restex.models;

public class Contact {
	private String nom;
	private String prenom;
	private String numero;
	private int contactId;
	
	public Contact() {
		super();
		
	}

	public Contact(String prenom, String nom, String numero) {
		this.prenom = prenom;
		this.nom = nom;
		this.numero = numero;
	}
	
	public Contact(Contact c) {
		super();
		this.prenom = c.prenom;
		this.nom = c.nom;
		this.numero = c.numero;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	@Override
	public String toString() {
		return "Contact [nom=" + nom + ", prenom=" + prenom + ", numero=" + numero + "]";
	}

}
