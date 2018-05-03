package co.simplon.simplonclick.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.simplonclick.dao.TypeInscriptionDAO;
import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.model.TypeInscription;
import co.simplon.simplonclick.service.TypeInscriptionService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class TypeInscriptionController {
	
	@Autowired
	private TypeInscriptionService typeInscriptionService;
	@Autowired
	private TypeInscriptionDAO typeInscriptionDAO;
	
	
	//INSERT INTO `simplonclick`.`type_inscription` (`id_type_inscription`, `type_inscription`) VALUES (?,?);
	@PostMapping(path = "/types-inscription")
	TypeInscription addTypeInscription(@Valid @RequestBody TypeInscription typeInscription) throws Exception {
		return typeInscriptionService.addTypeInscription(typeInscription);
	}

	//SELECT * FROM type_inscription;
	@GetMapping(path = "/types-inscription")
	public @ResponseBody Iterable<TypeInscription> getAllTypesInscription() throws Exception {
		return typeInscriptionService.getAllTypesInscription();
	}
	
	//SELECT * FROM type_inscription WHERE `id_type_inscription`=id;
	@GetMapping(path = "/type-inscription/{id}")
	ResponseEntity<TypeInscription> getTypeInscription(@PathVariable(value = "id") long id) throws Exception {
		TypeInscription typeInscription = typeInscriptionService.getTypeInscription(id);
		if (typeInscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(typeInscription);

	}
	
	//UPDATE type_inscription SET nom_colonne = "nouvelle valeur" WHERE id_type_inscription = id;
	@PutMapping(path = "/type-inscription/{id}")
	ResponseEntity<TypeInscription> updateTypeInscription(@PathVariable(value = "id") long id, @Valid @RequestBody TypeInscription typeInscription) throws Exception {
		TypeInscription typeInscriptionToBeUpdate = typeInscriptionService.getTypeInscription(id);
		if (typeInscriptionToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		typeInscriptionToBeUpdate.setId_type_inscription(typeInscription.getId_type_inscription());
		typeInscriptionToBeUpdate.setType_inscription(typeInscription.getType_inscription());
		

		// Mise à jour des attributs non null
		if (typeInscription.getId_type_inscription() != null)
			typeInscriptionToBeUpdate.setId_type_inscription(typeInscription.getId_type_inscription());

		if (typeInscription.getType_inscription() != null)
			typeInscriptionToBeUpdate.setType_inscription(typeInscription.getType_inscription());

		TypeInscription typeInscriptionUpdated = typeInscriptionService.editTypeInscription(id, typeInscriptionToBeUpdate);
		return ResponseEntity.ok(typeInscriptionUpdated);
	}
	
	//DELETE FROM type_inscription WHERE `id_type_inscription`= id;
	@DeleteMapping(path = "/type-inscription/{id}")
	ResponseEntity<TypeInscription> deleteTypeInscription(@PathVariable(value = "id") long id) throws Exception {
		TypeInscription typeInscription = typeInscriptionService.getTypeInscription(id);
		if (typeInscription == null)
			return ResponseEntity.notFound().build();

		typeInscriptionService.deleteTypeInscription(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/type-inscription/{id}/inscriptions")
	public ResponseEntity<?> recupererInscriptionsDeTypeInscription(@PathVariable(value = "id") long id) throws Exception {
		List<Inscription> inscriptions = null;
		TypeInscription typeInscription = typeInscriptionService.getTypeInscription(id);
		try {
		inscriptions = typeInscriptionDAO.recupererInscriptionsDeTypeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (typeInscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(inscriptions);
	}
	
	/**
	 * Permettre une recherche de type d'inscription(s) suivant des mots-clés
	 * 
	 * @param recherche
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/types-inscription/{recherche}")
	public ResponseEntity<List<TypeInscription>> recupererTypesTriees(@PathVariable(value = "recherche") String recherche)
			throws Exception {
		// @RequestParam(required = false, value="nom") String nom

		List<TypeInscription> listeTypeInscription = typeInscriptionDAO.recupererTypesInscriptionTriees(recherche);
		if (listeTypeInscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listeTypeInscription);
	}

}
