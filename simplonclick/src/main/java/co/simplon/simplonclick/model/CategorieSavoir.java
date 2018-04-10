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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categorie_savoir")

public class CategorieSavoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	private Long id_categorie_savoir;
	private String nom_categorie_savoir;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categorie_savoir")
	@JsonIgnore
    private Set<Savoir> savoirs = new HashSet<>();
	
	public CategorieSavoir() {
		
	}
	
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

	public Set<Savoir> getSavoirs() {
		return savoirs;
	}

	public void setSavoirs(Set<Savoir> savoirs) {
		this.savoirs = savoirs;
	}

}
