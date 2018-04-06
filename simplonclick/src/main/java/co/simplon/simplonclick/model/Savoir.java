package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "savoir")

public class Savoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_savoir;
	private String nom_savoir;
	
	public Long getId_savoir() {
		return id_savoir;
	}
	public void setId_savoir(Long id_savoir) {
		this.id_savoir = id_savoir;
	}
	public String getNom_savoir() {
		return nom_savoir;
	}
	public void setNom_savoir(String nom_savoir) {
		this.nom_savoir = nom_savoir;
	}

}
