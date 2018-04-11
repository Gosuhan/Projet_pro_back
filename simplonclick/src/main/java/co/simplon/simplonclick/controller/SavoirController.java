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

import co.simplon.simplonclick.dao.SavoirDAO;
import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.service.SavoirService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class SavoirController {
	
	@Autowired
	private SavoirService savoirService;
	@Autowired
	private SavoirDAO savoirDAO;
	
	//INSERT INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`) VALUES (?,?);
	@PostMapping(path = "/savoir")
	Savoir addSavoir(@Valid @RequestBody Savoir savoir) throws Exception {
		return savoirService.addSavoir(savoir);
	}

	//SELECT * FROM categorie_savoir;
	@GetMapping(path = "/savoirs")
	public @ResponseBody Iterable<Savoir> getAllSavoirs() throws Exception {
		return savoirService.getAllSavoirs();
	}
	
	//SELECT * FROM savoir WHERE `id_savoir`=id;
	@GetMapping(path = "/savoir/{id}")
	ResponseEntity<Savoir> getSavoir(@PathVariable(value = "id") long id) throws Exception {
		Savoir savoir = savoirService.getSavoir(id);
		if (savoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(savoir);

	}
	
	//UPDATE savoir SET nom_colonne = "nouvelle valeur" WHERE id_savoir = id;
	@PutMapping(path = "/savoir/{id}")
	ResponseEntity<Savoir> updateSavoir(@PathVariable(value = "id") long id, @Valid @RequestBody Savoir savoir) throws Exception {
		Savoir savoirToBeUpdate = savoirService.getSavoir(id);
		if (savoirToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		savoirToBeUpdate.setId_savoir(savoir.getId_savoir());
		savoirToBeUpdate.setNom_savoir(savoir.getNom_savoir());
		

		// Mise à jour des attributs non null
		if (savoir.getId_savoir() != null)
			savoirToBeUpdate.setId_savoir(savoir.getId_savoir());

		if (savoir.getNom_savoir() != null)
			savoirToBeUpdate.setNom_savoir(savoir.getNom_savoir());

		Savoir savoirUpdated = savoirService.editSavoir(id, savoirToBeUpdate);
		return ResponseEntity.ok(savoirUpdated);
	}
	
	//DELETE FROM savoir WHERE `id_savoir`= id;
	@DeleteMapping(path = "/savoir/{id}")
	ResponseEntity<Savoir> deleteSavoir(@PathVariable(value = "id") long id) throws Exception {
		Savoir savoir = savoirService.getSavoir(id);
		if (savoir == null)
			return ResponseEntity.notFound().build();

		savoirService.deleteSavoir(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/savoir/{id}/ressources")
	public ResponseEntity<?> recupererRessourcesDeSavoir(@PathVariable(value = "id") long id) throws Exception {
		List<Ressource> ressources = 	null;
		Savoir savoir = savoirService.getSavoir(id);
		try {
		ressources = savoirDAO.recupererRessourcesDeSavoir(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (savoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ressources);
	}

}
