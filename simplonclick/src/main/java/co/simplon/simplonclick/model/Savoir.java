package co.simplon.simplonclick.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "savoir")

public class Savoir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	private Long id_savoir;
	private String nom_savoir;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "savoir")
	@JsonManagedReference
    private Set<Inscription> inscriptions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "savoir")
	@JsonManagedReference
    private Set<Ressource> ressources = new HashSet<>();
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY) //Optional = Autorisation NULL
    @JoinColumn(name = "categorie_savoir_id_categorie_savoir", nullable = true) // + nullable
	@JsonBackReference
	
    private CategorieSavoir categorie_savoir;
	
	public Savoir() {
		
	}
	
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

	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Set<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}

	public CategorieSavoir getCategorie_savoir() {
		return categorie_savoir;
	}

	public void setCategorie_savoir(CategorieSavoir categorie_savoir) {
		this.categorie_savoir = categorie_savoir;
	}

}
