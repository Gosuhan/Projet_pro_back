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

import co.simplon.simplonclick.dao.InscriptionDAO;
import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.service.InscriptionService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class InscriptionController {
	
	@Autowired
	private InscriptionService inscriptionService;
	@Autowired
	private InscriptionDAO inscriptionDAO;
	
	//INSERT INTO `simplonclick`.`inscription` (`id_type_inscription`) VALUES (?);
	@PostMapping(path = "/inscription")
	Inscription addInscription(@Valid @RequestBody Inscription inscription) throws Exception {
		return inscriptionService.addInscription(inscription);
	}

	//SELECT * FROM inscription;
	@GetMapping(path = "/inscriptions")
	public @ResponseBody Iterable<Inscription> getAllInscriptions() throws Exception {
		return inscriptionService.getAllInscriptions();
	}
	
	//SELECT * FROM inscription WHERE `id_inscription`=id;
	@GetMapping(path = "/inscription/{id}")
	ResponseEntity<Inscription> getInscription(@PathVariable(value = "id") long id) throws Exception {
		Inscription inscription = inscriptionService.getInscription(id);
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(inscription);

	}
	
	//UPDATE CERTAINEMENT PAS UTILE /!\ A SUPPRIMER SI NON UTILISE PAR LA SUITE (/!\ CODE MORT)
	//UPDATE inscription SET nom_colonne = "nouvelle valeur" WHERE id_inscription = id;
	@PutMapping(path = "/inscription/{id}")
	ResponseEntity<Inscription> updateInscription(@PathVariable(value = "id") long id, @Valid @RequestBody Inscription inscription) throws Exception {
		Inscription inscriptionToBeUpdate = inscriptionService.getInscription(id);
		if (inscriptionToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		inscriptionToBeUpdate.setId_inscription(inscription.getId_inscription());
		

		// Mise à jour des attributs non null
		if (inscription.getId_inscription() != null)
			inscriptionToBeUpdate.setId_inscription(inscription.getId_inscription());

		Inscription inscriptionUpdated = inscriptionService.editInscription(id, inscriptionToBeUpdate);
		return ResponseEntity.ok(inscriptionUpdated);
	}
	
	//DELETE FROM inscription WHERE `id_inscription`= id;
	@DeleteMapping(path = "/inscription/{id}")
	ResponseEntity<Inscription> deleteInscription(@PathVariable(value = "id") long id) throws Exception {
		Inscription inscription = inscriptionService.getInscription(id);
		if (inscription == null)
			return ResponseEntity.notFound().build();

		inscriptionService.deleteInscription(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/inscription/{id}/membre")
	public ResponseEntity<?> recupererMembresDeInscription(@PathVariable(value = "id") long id) throws Exception {
		List<Membre> membres = null;
		Inscription inscription = inscriptionService.getInscription(id);
		try {
		membres = inscriptionDAO.recupererMembresDeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(membres);
	}

}
