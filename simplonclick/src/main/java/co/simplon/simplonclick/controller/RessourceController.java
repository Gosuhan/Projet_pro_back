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

import co.simplon.simplonclick.dao.RessourceDAO;
import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.service.RessourceService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class RessourceController {
	
	@Autowired
	private RessourceService ressourceService;
	@Autowired
	private RessourceDAO ressourceDAO;
	
	//INSERT INTO `simplonclick`.`ressource` (`id_ressource`, `url`, `nom_ressource`) VALUES (?,?,?);
	@PostMapping(path = "/ressources")
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
	
	@GetMapping(path = "/ressource/{id}/savoir")
	public ResponseEntity<?> recupererSavoirDeRessource(@PathVariable(value = "id") long id) throws Exception {
		Savoir savoir = 	null;
		Ressource ressource = ressourceService.getRessource(id);
		try {
		savoir = ressourceDAO.recupererSavoirDeRessource(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (ressource == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(savoir);
	}
	
	/**
	 * Ajouter une ressource à un savoir
	 * 
	 * @param id_ressource
	 * @param id_savoir
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/savoir/{id_savoir}/addressource/{id_ressource}")		
	ResponseEntity<?> lierRessourceaSavoir(@PathVariable(value = "id_ressource") long id_ressource, @PathVariable(value = "id_savoir") long id_savoir) throws Exception {
		try {
			ressourceDAO.lierRessourceaSavoir(id_ressource, id_savoir);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	/**
	 * Retirer une ressource à un savoir
	 * 
	 * @param id_ressource
	 * @param id_savoir
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/savoir/{id_savoir}/delressource/{id_ressource}")		
	ResponseEntity<?> delierRessourceaSavoir(@PathVariable(value = "id_ressource") long id_ressource, @PathVariable(value = "id_savoir") long id_savoir) throws Exception {
		try {
			ressourceDAO.delierRessourceaSavoir(id_ressource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	/**
	 * Permettre une recherche de ressource(s) suivant des mots-clés
	 * 
	 * @param recherche
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/ressources/{recherche}")
	public ResponseEntity<List<Ressource>> recupererRessourcesTriees(@PathVariable(value = "recherche") String recherche)
			throws Exception {
		// @RequestParam(required = false, value="nom") String nom

		List<Ressource> listeRessource = ressourceDAO.recupererRessourcesTriees(recherche);
		if (listeRessource == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listeRessource);
	}

}
