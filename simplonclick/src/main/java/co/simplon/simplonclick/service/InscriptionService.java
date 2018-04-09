package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.repository.InscriptionRepository;

@Service
public class InscriptionService {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	
	public Iterable<Inscription> getAllInscriptions() throws Exception {
		return inscriptionRepository.findAll();
	}
	
	public Inscription getInscription(Long id) throws Exception {
		return inscriptionRepository.findOne(id);
	}
	
	public void deleteInscription(Long id) {
		inscriptionRepository.delete(id);
	}
	
	public Inscription addInscription(Inscription inscription) throws Exception {
		return inscriptionRepository.save(inscription);
	}

	public Inscription editInscription(Long id, Inscription inscription) throws Exception {
		return inscriptionRepository.save(inscription);
	}

	public void clearInscriptionTable() {
		inscriptionRepository.deleteAll();
	}

}
