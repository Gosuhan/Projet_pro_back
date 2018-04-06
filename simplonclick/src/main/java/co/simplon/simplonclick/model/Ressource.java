package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ressource")

public class Ressource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_ressource;
	private String url;
	private String nom_ressource;
	
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

}
