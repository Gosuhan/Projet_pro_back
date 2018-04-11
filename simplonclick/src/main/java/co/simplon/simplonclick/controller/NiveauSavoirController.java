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

import co.simplon.simplonclick.dao.NiveauSavoirDAO;
import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.model.NiveauSavoir;
import co.simplon.simplonclick.service.NiveauSavoirService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class NiveauSavoirController {
	
	@Autowired
	private NiveauSavoirService niveauSavoirService;
	@Autowired
	private NiveauSavoirDAO niveauSavoirDAO;
	
	//INSERT INTO `simplonclick`.`niveau_savoir` (`id_niveau_savoir`, `niveau_savoir`) VALUES (?,?);
	@PostMapping(path = "/niveau-savoir")
	NiveauSavoir addNiveauSavoir(@Valid @RequestBody NiveauSavoir niveauSavoir) throws Exception {
		return niveauSavoirService.addNiveauSavoir(niveauSavoir);
	}

	//SELECT * FROM niveau_savoir;
	@GetMapping(path = "/niveaux-savoir")
	public @ResponseBody Iterable<NiveauSavoir> getAllNiveauxSavoir() throws Exception {
		return niveauSavoirService.getAllNiveauxSavoir();
	}
	
	//SELECT * FROM niveau_savoir WHERE `id_niveau_savoir`=id;
	@GetMapping(path = "/niveau-savoir/{id}")
	ResponseEntity<NiveauSavoir> getNiveauSavoir(@PathVariable(value = "id") long id) throws Exception {
		NiveauSavoir niveauSavoir = niveauSavoirService.getNiveauSavoir(id);
		if (niveauSavoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(niveauSavoir);

	}
	
	//UPDATE niveau_savoir SET nom_colonne = "nouvelle valeur" WHERE id_niveau_savoir = id;
	@PutMapping(path = "/niveau-savoir/{id}")
	ResponseEntity<NiveauSavoir> updateNiveauSavoir(@PathVariable(value = "id") long id, @Valid @RequestBody NiveauSavoir niveauSavoir) throws Exception {
		NiveauSavoir niveauSavoirToBeUpdate = niveauSavoirService.getNiveauSavoir(id);
		if (niveauSavoirToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		niveauSavoirToBeUpdate.setId_niveau_savoir(niveauSavoir.getId_niveau_savoir());
		niveauSavoirToBeUpdate.setNiveau_savoir(niveauSavoir.getNiveau_savoir());
		

		// Mise à jour des attributs non null
		if (niveauSavoir.getId_niveau_savoir() != null)
			niveauSavoirToBeUpdate.setId_niveau_savoir(niveauSavoir.getId_niveau_savoir());

		if (niveauSavoir.getNiveau_savoir() != null)
			niveauSavoirToBeUpdate.setNiveau_savoir(niveauSavoir.getNiveau_savoir());

		NiveauSavoir niveauSavoirUpdated = niveauSavoirService.editNiveauSavoir(id, niveauSavoirToBeUpdate);
		return ResponseEntity.ok(niveauSavoirUpdated);
	}
	
	//DELETE FROM niveau_savoir WHERE `id_niveau_savoir`= id;
	@DeleteMapping(path = "/niveau-savoir/{id}")
	ResponseEntity<NiveauSavoir> deleteNiveauSavoir(@PathVariable(value = "id") long id) throws Exception {
		NiveauSavoir niveauSavoir = niveauSavoirService.getNiveauSavoir(id);
		if (niveauSavoir == null)
			return ResponseEntity.notFound().build();

		niveauSavoirService.deleteNiveauSavoir(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/niveau-savoir/{id}/inscriptions")
	public ResponseEntity<?> recupererInscriptionsDeNiveauSavoir(@PathVariable(value = "id") long id) throws Exception {
		List<Inscription> inscriptions = null;
		NiveauSavoir niveauSavoir = niveauSavoirService.getNiveauSavoir(id);
		try {
		inscriptions = niveauSavoirDAO.recupererInscriptionsDeSavoir(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (niveauSavoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(inscriptions);
	}

}
