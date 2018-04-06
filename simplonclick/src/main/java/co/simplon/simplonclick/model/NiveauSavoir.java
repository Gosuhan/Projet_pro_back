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

@Entity
@Table(name = "niveau_savoir")

public class NiveauSavoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_niveau_savoir;
	private String niveau_savoir;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "niveau_savoir")
    private Set<Inscription> inscriptions = new HashSet<>();
	
	public NiveauSavoir() {
		
	}
	
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

	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

}
