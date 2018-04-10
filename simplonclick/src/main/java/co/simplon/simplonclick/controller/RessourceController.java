package co.simplon.simplonclick.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.service.RessourceService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class RessourceController {
	
	@Autowired
	private RessourceService ressourceService;
	
	//INSERT INTO `simplonclick`.`ressource` (`id_ressource`, `url`, `nom_ressource`) VALUES (?,?,?);
	@PostMapping(path = "/ressource")
	Ressource addRessource(@Valid @RequestBody Ressource ressource) throws Exception {
		return ressourceService.addRessource(ressource);
	}

	//SELECT * FROM ressource;
	@GetMapping(path = "/ressources")
	public @ResponseBody Iterable<Ressource> getAllRessources() throws Exception {
		return ressourceService.getAllRessources();
	}
	
	//SELECT * FROM ressource WHERE `id_ressource`=id;
	@GetMapping(path = "/ressource/{id}")
	ResponseEntity<Ressource> getRessource(@PathVariable(value = "id") long id) throws Exception {
		Ressource ressource = ressourceService.getRessource(id);
		if (ressource == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ressource);

	}
	
	//UPDATE ressource SET nom_colonne = "nouvelle valeur" WHERE id_ressource = id;
	@PutMapping(path = "/ressource/{id}")
	ResponseEntity<Ressource> updateRessource(@PathVariable(value = "id") long id, @Valid @RequestBody Ressource ressource) throws Exception {
		Ressource ressourceToBeUpdate = ressourceService.getRessource(id);
		if (ressourceToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		ressourceToBeUpdate.setId_ressource(ressource.getId_ressource());
		ressourceToBeUpdate.setUrl(ressource.getUrl());
		ressourceToBeUpdate.setNom_ressource(ressource.getNom_ressource());
		

		// Mise à jour des attributs non null
		if (ressource.getId_ressource() != null)
			ressourceToBeUpdate.setId_ressource(ressource.getId_ressource());

		if (ressource.getUrl() != null)
			ressourceToBeUpdate.setUrl(ressource.getUrl());
		
		if(ressource.getNom_ressource() != null)
			ressourceToBeUpdate.setNom_ressource(ressource.getNom_ressource());

		Ressource ressourceUpdated = ressourceService.editRessource(id, ressourceToBeUpdate);
		return ResponseEntity.ok(ressourceUpdated);
	}
	
	//DELETE FROM savoir WHERE `id_savoir`= id;
	@DeleteMapping(path = "/ressource/{id}")
	ResponseEntity<Ressource> deleteRessource(@PathVariable(value = "id") long id) throws Exception {
		Ressource savoir = ressourceService.getRessource(id);
		if (savoir == null)
			return ResponseEntity.notFound().build();

		ressourceService.deleteRessource(id);
		return ResponseEntity.ok().build();
	}

}
