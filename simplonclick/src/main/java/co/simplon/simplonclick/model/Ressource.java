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

@Entity
@Table(name = "ressource")

public class Ressource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	private Long id_ressource;
	private String url;
	private String nom_ressource;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "savoir_id_savoir", nullable = true)
	@JsonBackReference
	
    private Savoir savoir;
	
	public Ressource() {
		
	}
	
	public Long getId_ressource() {
		return id_ressource;
	}
	public void setId_ressource(Long id_ressource) {
		this.id_ressource = id_ressource;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNom_ressource() {
		return nom_ressource;
	}
	public void setNom_ressource(String nom_ressource) {
		this.nom_ressource = nom_ressource;
	}

	public Savoir getSavoir() {
		return savoir;
	}

	public void setSavoir(Savoir savoir) {
		this.savoir = savoir;
	}

}
