package co.simplon.simplonclick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_inscription")

public class TypeInscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id_type_inscription;
	private String type_inscription;
	
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

}
