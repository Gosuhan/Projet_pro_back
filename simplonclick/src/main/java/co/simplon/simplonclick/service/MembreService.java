package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.repository.MembreRepository;

@Service
public class MembreService {

	@Autowired
	private MembreRepository membreRepository;
	
	public Iterable<Membre> getAllMembres() throws Exception {
		return membreRepository.findAll();
	}
	
	public Membre getMembre(Long id) throws Exception {
		return membreRepository.findOne(id);
	}
	
	public void deleteMembre(Long id) {
		membreRepository.delete(id);
	}
	
	public Membre addMembre(Membre membre) throws Exception {
		return membreRepository.save(membre);
	}

	public Membre editMembre(Long id, Membre membre) throws Exception {
		return membreRepository.save(membre);
	}

	public void clearMembreTable() {
		membreRepository.deleteAll();
	}
	
}
