package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membre")

public class Membre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_membre;
	private String pseudo;
	private String password;
	private String nom;
	private String prenom;
	private boolean admin = false;
	private String email;
	private String image;
	private String fonction;
	private String niveau_general;
	private String disponibilite_habituelle;
	private boolean disponibilite_actuelle;
	
	public Long getId_membre() {
		return id_membre;
	}
	public void setId_membre(Long id_membre) {
		this.id_membre = id_membre;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getNiveau_general() {
		return niveau_general;
	}
	public void setNiveau_general(String niveau_general) {
		this.niveau_general = niveau_general;
	}
	public String getDisponibilite_habituelle() {
		return disponibilite_habituelle;
	}
	public void setDisponibilite_habituelle(String disponibilite_habituelle) {
		this.disponibilite_habituelle = disponibilite_habituelle;
	}
	public boolean isDisponibilite_actuelle() {
		return disponibilite_actuelle;
	}
	public void setDisponibilite_actuelle(boolean disponibilite_actuelle) {
		this.disponibilite_actuelle = disponibilite_actuelle;
	}
	

}
