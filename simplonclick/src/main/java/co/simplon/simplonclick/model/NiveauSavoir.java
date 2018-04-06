package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "niveau_savoir")

public class NiveauSavoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_niveau_savoir;
	private String niveau_savoir;
	
	public Long getId_niveau_savoir() {
		return id_niveau_savoir;
	}
	public void setId_niveau_savoir(Long id_niveau_savoir) {
		this.id_niveau_savoir = id_niveau_savoir;
	}
	public String getNiveau_savoir() {
		return niveau_savoir;
	}
	public void setNiveau_savoir(String niveau_savoir) {
		this.niveau_savoir = niveau_savoir;
	}

}
