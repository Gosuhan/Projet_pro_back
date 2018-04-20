package co.simplon.simplonclick.controller;


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
import co.simplon.simplonclick.model.NiveauSavoir;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.model.TypeInscription;
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
	public ResponseEntity<?> recupererMembreDeInscription(@PathVariable(value = "id") long id) throws Exception {
		Membre membre = null;
		Inscription inscription = inscriptionService.getInscription(id);
		try {
		membre = inscriptionDAO.recupererMembreDeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(membre);
	}
	
	@GetMapping(path = "/inscription/{id}/savoir")
	public ResponseEntity<?> recupererSavoirDeInscription(@PathVariable(value = "id") long id) throws Exception {
		Savoir savoir = null;
		Inscription inscription = inscriptionService.getInscription(id);
		try {
			savoir = inscriptionDAO.recupererSavoirDeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(savoir);
	}
	
	@GetMapping(path = "/inscription/{id}/type-inscription")
	public ResponseEntity<?> recupererTypeInscriptionDeInscription(@PathVariable(value = "id") long id) throws Exception {
		TypeInscription typeInscription = null;
		Inscription inscription = inscriptionService.getInscription(id);
		try {
			typeInscription = inscriptionDAO.recupererTypeInscriptionDeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(typeInscription);
	}
	
	@GetMapping(path = "/inscription/{id}/niveau-savoir")
	public ResponseEntity<?> recupererNiveauSavoirDeInscription(@PathVariable(value = "id") long id) throws Exception {
		NiveauSavoir niveauSavoir = null;
		Inscription inscription = inscriptionService.getInscription(id);
		try {
			niveauSavoir = inscriptionDAO.recupererNiveauSavoirDeInscription(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (inscription == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(niveauSavoir);
	}
	
	/**
	 * Ajouter une inscription à un membre
	 * 
	 * @param id_inscription
	 * @param id_membre
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/membre/{id_membre}/addinscription/{id_inscription}")		
	ResponseEntity<?> lierInscriptionaMembre(@PathVariable(value = "id_inscription") long id_inscription, @PathVariable(value = "id_membre") long id_membre) throws Exception {
		try {
			inscriptionDAO.lierInscriptionaMembre(id_inscription, id_membre);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	/**
	 * Ajouter une inscription à un niveau de savoir
	 * 
	 * @param id_inscription
	 * @param id_niveau_savoir
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/niveau-savoir/{id_niveau_savoir}/addinscription/{id_inscription}")		
	ResponseEntity<?> lierInscriptionaNiveauSavoir(@PathVariable(value = "id_inscription") long id_inscription, @PathVariable(value = "id_niveau_savoir") long id_niveau_savoir) throws Exception {
		try {
			inscriptionDAO.lierInscriptionaNiveauSavoir(id_inscription, id_niveau_savoir);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	/**
	 * Ajouter une inscription à un type d'inscription
	 * 
	 * @param id_inscription
	 * @param id_type_inscription
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/type-inscription/{id_type_inscription}/addinscription/{id_inscription}")		
	ResponseEntity<?> lierInscriptionaTypeInscription(@PathVariable(value = "id_inscription") long id_inscription, @PathVariable(value = "id_type_inscription") long id_type_inscription) throws Exception {
		try {
			inscriptionDAO.lierInscriptionaTypeInscription(id_inscription, id_type_inscription);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	/**
	 * Ajouter une inscription à un savoir
	 * 
	 * @param id_inscription
	 * @param id_savoir
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/savoir/{id_savoir}/addinscription/{id_inscription}")		
	ResponseEntity<?> lierInscriptionaSavoir(@PathVariable(value = "id_inscription") long id_inscription, @PathVariable(value = "id_savoir") long id_savoir) throws Exception {
		try {
			inscriptionDAO.lierInscriptionaSavoir(id_inscription, id_savoir);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

}
