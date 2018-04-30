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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "type_inscription",
uniqueConstraints=
@UniqueConstraint(columnNames={"type_inscription"}))

public class TypeInscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	private Long id_type_inscription;
	private String type_inscription;
	
	@OneToMany(cascade = CascadeType.ALL/*, fetch = FetchType.LAZY*/, mappedBy = "type_inscription")
	@JsonIgnoreProperties(value = {"type_inscription", "membre", "savoir", "niveau_savoir"}, allowSetters = true)
    private Set<Inscription> inscriptions = new HashSet<>();
	
	public TypeInscription() {
		
	}
	
	public Long getId_type_inscription() {
		return id_type_inscription;
	}
	public void setId_type_inscription(Long id_type_inscription) {
		this.id_type_inscription = id_type_inscription;
	}
	public String getType_inscription() {
		return type_inscription;
	}
	public void setType_inscription(String type_inscription) {
		this.type_inscription = type_inscription;
	}

	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

}
