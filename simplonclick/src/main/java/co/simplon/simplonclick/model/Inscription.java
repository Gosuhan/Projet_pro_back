package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "inscription")

public class Inscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	private Long id_inscription;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id_membre", nullable = true)
	@JsonIgnore
    private Membre membre;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_inscription_id_type_inscription", nullable = true)
	@JsonIgnore
    private TypeInscription type_inscription;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_savoir_id_niveau_savoir", nullable = true)
	@JsonIgnore
    private NiveauSavoir niveau_savoir;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "savoir_id_savoir", nullable = true)
	@JsonIgnore
	
    private Savoir savoir;
	
	public Inscription() {
		
	}

	public Long getId_inscription() {
		return id_inscription;
	}

	public void setId_inscription(Long id_inscription) {
		this.id_inscription = id_inscription;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public TypeInscription getType_inscription() {
		return type_inscription;
	}

	public void setType_inscription(TypeInscription type_inscription) {
		this.type_inscription = type_inscription;
	}

	public NiveauSavoir getNiveau_savoir() {
		return niveau_savoir;
	}

	public void setNiveau_savoir(NiveauSavoir niveau_savoir) {
		this.niveau_savoir = niveau_savoir;
	}

	public Savoir getSavoir() {
		return savoir;
	}

	public void setSavoir(Savoir savoir) {
		this.savoir = savoir;
	}

}
