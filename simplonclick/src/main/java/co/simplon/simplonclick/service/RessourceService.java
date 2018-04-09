package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.repository.RessourceRepository;

@Service
public class RessourceService {
	
	@Autowired
	private RessourceRepository ressourceRepository;
	
	public Iterable<Ressource> getAllRessources() throws Exception {
		return ressourceRepository.findAll();
	}
	
	public Ressource getRessource(Long id) throws Exception {
		return ressourceRepository.findOne(id);
	}
	
	public void deleteRessource(Long id) {
		ressourceRepository.delete(id);
	}
	
	public Ressource addRessource(Ressource ressource) throws Exception {
		return ressourceRepository.save(ressource);
	}

	public Ressource editRessource(Long id, Ressource ressource) throws Exception {
		return ressourceRepository.save(ressource);
	}

	public void clearRessourceTable() {
		ressourceRepository.deleteAll();
	}

}
