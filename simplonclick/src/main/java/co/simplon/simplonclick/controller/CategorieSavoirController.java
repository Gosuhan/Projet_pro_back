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

import co.simplon.simplonclick.dao.CategorieSavoirDAO;
import co.simplon.simplonclick.model.CategorieSavoir;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.service.CategorieSavoirService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class CategorieSavoirController {
	
	@Autowired
	private CategorieSavoirService categorieSavoirService;
	@Autowired
	private CategorieSavoirDAO categorieSavoirDAO;
	
	//INSERT INTO `simplonclick`.`categorie_savoir` (`id_categorie_savoir`, `nom_categorie_savoir`) VALUES (?,?);
	@PostMapping(path = "/categorie-savoir")
	CategorieSavoir addCategorieSavoir(@Valid @RequestBody CategorieSavoir categorieSavoir) throws Exception {
		return categorieSavoirService.addCategorieSavoir(categorieSavoir);
	}

	//SELECT * FROM categorie_savoir;
	@GetMapping(path = "/categories-savoir")
	public @ResponseBody Iterable<CategorieSavoir> getAllCategoriesSavoir() throws Exception {
		return categorieSavoirService.getAllCategoriesSavoir();
	}
	
	//SELECT * FROM categorie_savoir WHERE `id_categorie_savoir`=id;
	@GetMapping(path = "/categorie-savoir/{id}")
	ResponseEntity<CategorieSavoir> getCategorieSavoir(@PathVariable(value = "id") long id) throws Exception {
		CategorieSavoir categorieSavoir = categorieSavoirService.getCategorieSavoir(id);
		if (categorieSavoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(categorieSavoir);

	}
	
	//UPDATE categorie_savoir SET nom_colonne = "nouvelle valeur" WHERE id_categorie_savoir = id;
	@PutMapping(path = "/categorie-savoir/{id}")
	ResponseEntity<CategorieSavoir> updateCategorieSavoir(@PathVariable(value = "id") long id, @Valid @RequestBody CategorieSavoir categorieSavoir) throws Exception {
		CategorieSavoir categorieSavoirToBeUpdate = categorieSavoirService.getCategorieSavoir(id);
		if (categorieSavoirToBeUpdate == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		categorieSavoirToBeUpdate.setId_categorie_savoir(categorieSavoir.getId_categorie_savoir());
		categorieSavoirToBeUpdate.setNom_categorie_savoir(categorieSavoir.getNom_categorie_savoir());
		

		// Mise à jour des attributs non null
		if (categorieSavoir.getId_categorie_savoir() != null)
			categorieSavoirToBeUpdate.setId_categorie_savoir(categorieSavoir.getId_categorie_savoir());

		if (categorieSavoir.getNom_categorie_savoir() != null)
			categorieSavoirToBeUpdate.setNom_categorie_savoir(categorieSavoir.getNom_categorie_savoir());

		CategorieSavoir categorieSavoirUpdated = categorieSavoirService.editCategorieSavoir(id, categorieSavoirToBeUpdate);
		return ResponseEntity.ok(categorieSavoirUpdated);
	}
	
	//DELETE FROM categorie_savoir WHERE `id_categorie_savoir`= id;
	@DeleteMapping(path = "/categorie-savoir/{id}")
	ResponseEntity<CategorieSavoir> deleteCategorieSavoir(@PathVariable(value = "id") long id) throws Exception {
		CategorieSavoir categorieSavoir = categorieSavoirService.getCategorieSavoir(id);
		if (categorieSavoir == null)
			return ResponseEntity.notFound().build();

		categorieSavoirService.deleteCategorieSavoir(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/categorie-savoir/{id}/savoirs")
	public ResponseEntity<?> recupererSavoirsDeCategorieSavoir(@PathVariable(value = "id") long id) throws Exception {
		List<Savoir> savoirs = 	null;
		CategorieSavoir categorieSavoir = categorieSavoirService.getCategorieSavoir(id);
		try {
		savoirs = categorieSavoirDAO.recupererSavoirsDeCategorieSavoir(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		if (categorieSavoir == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(savoirs);
	}

}
