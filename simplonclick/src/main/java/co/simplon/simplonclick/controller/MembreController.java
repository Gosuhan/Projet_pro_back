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

import co.simplon.simplonclick.dao.MembreDAO;
import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.service.MembreService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class MembreController {
	
	@Autowired
	private MembreService membreService;
	@Autowired
	private MembreDAO membreDAO;
	
	//INSERT INTO `simplonclick`.`membre` (`id_membre`, `pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES (?,?,?,?,?);
	@PostMapping(path = "/membre")
	Membre addMembre(@Valid @RequestBody Membre membre) throws Exception {
		return membreService.addMembre(membre);
	}

	//SELECT * FROM membre;
	@GetMapping(path = "/membres")
	public @ResponseBody Iterable<Membre> getAllMembres() throws Exception {
		return membreService.getAllMembres();
	}
	
	//SELECT * FROM membre WHERE `id_membre`=id;
	@GetMapping(path = "/membre/{id}")
	ResponseEntity<Membre> getMembre(@PathVariable(value = "id") long id) throws Exception {
		Membre membre = membreService.getMembre(id);
		if (membre == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(membre);

	}
	
	//UPDATE membre SET nom_colonne = "nouvelle valeur" WHERE id_membre = id;
	@PutMapping(path = "/membre/{id}")
	ResponseEntity<Membre> updateMembre(@PathVariable(value = "id") long id, @Valid @RequestBody Membre membre) throws Exception {
		Membre membreToBeUpdate = membreService.getMembre(id);
		if (membreToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		membreToBeUpdate.setId_membre(membre.getId_membre());
		membreToBeUpdate.setPseudo(membre.getPseudo());
		membreToBeUpdate.setPassword(membre.getPassword());
		membreToBeUpdate.setNom(membre.getNom());
		membreToBeUpdate.setPrenom(membre.getPrenom());
		membreToBeUpdate.setEmail(membre.getEmail());
		membreToBeUpdate.setPseudo_slack(membre.getPseudo_slack());
		membreToBeUpdate.setImage(membre.getImage());
		membreToBeUpdate.setFonction(membre.getFonction());
		membreToBeUpdate.setNiveau_general(membre.getNiveau_general());
		membreToBeUpdate.setDisponibilite_habituelle(membre.getDisponibilite_habituelle());
		

		// Mise à jour des attributs non null
		if (membre.getPseudo() != null)
			membreToBeUpdate.setPseudo(membre.getPseudo());

		if (membre.getPassword() != null)
			membreToBeUpdate.setPassword(membre.getPassword());

		if (membre.getNom() != null)
			membreToBeUpdate.setNom(membre.getNom());

		if (membre.getPrenom() != null)
			membreToBeUpdate.setPrenom(membre.getPrenom());

		if (membre.getEmail() != null)
			membreToBeUpdate.setEmail(membre.getEmail());
		
		if (membre.getPseudo_slack() != null)
			membreToBeUpdate.setPseudo_slack(membre.getPseudo_slack());
		
		if (membre.getImage() != null)
			membreToBeUpdate.setImage(membre.getImage());
		
		if (membre.getFonction() != null)
			membreToBeUpdate.setFonction(membre.getFonction());
		
		if (membre.getNiveau_general() != null)
			membreToBeUpdate.setNiveau_general(membre.getNiveau_general());
		
		if (membre.getDisponibilite_habituelle() != null)
			membreToBeUpdate.setDisponibilite_habituelle(membre.getDisponibilite_habituelle());

		Membre membreUpdated = membreService.editMembre(id, membreToBeUpdate);
		return ResponseEntity.ok(membreUpdated);
	}
	
	//DELETE FROM membre WHERE `id_membre`= id;
	@DeleteMapping(path = "/membre/{id}")
	ResponseEntity<Membre> deleteMembre(@PathVariable(value = "id") long id) throws Exception {
		Membre membre = membreService.getMembre(id);
		if (membre == null)
			return ResponseEntity.notFound().build();

		membreService.deleteMembre(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/membre/{id}/inscriptions")
	public ResponseEntity<?> recupererInscriptionsDeMembre(@PathVariable(value = "id") long id) throws Exception {
		List<Inscription> inscriptions = null;
		Membre membre = membreService.getMembre(id);
		try {
		inscriptions = membreDAO.recupererInscriptionsDeMembre(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (membre == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(inscriptions);
	}

}
