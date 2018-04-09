package co.simplon.simplonclick.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "membre")

public class Membre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
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
	private boolean disponibilite_actuelle = false;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "membre")
    private Set<Inscription> inscriptions = new HashSet<>();
	
	public Membre() {
		
	}
	
	/*
	 * method to change boolean admin
	 */
	public void estAdmin() {
		admin = !admin;
	}
	
	/*
	 * method to change boolean disponibilite_actuelle
	 */
	public void estDisponible() {
		disponibilite_actuelle = !disponibilite_actuelle;
	}
	
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

	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	

}
