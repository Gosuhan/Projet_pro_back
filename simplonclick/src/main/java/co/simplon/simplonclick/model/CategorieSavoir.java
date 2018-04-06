package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie_savoir")

public class CategorieSavoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_categorie_savoir;
	private String nom_categorie_savoir;
	
	public Long getId_categorie_savoir() {
		return id_categorie_savoir;
	}
	public void setId_categorie_savoir(Long id_categorie_savoir) {
		this.id_categorie_savoir = id_categorie_savoir;
	}
	public String getNom_categorie_savoir() {
		return nom_categorie_savoir;
	}
	public void setNom_categorie_savoir(String nom_categorie_savoir) {
		this.nom_categorie_savoir = nom_categorie_savoir;
	}

}
