package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.NiveauSavoir;
import co.simplon.simplonclick.repository.NiveauSavoirRepository;


@Service
public class NiveauSavoirService {
	
	@Autowired
	private NiveauSavoirRepository niveauSavoirRepository;
	
	public Iterable<NiveauSavoir> getAllNiveauxSavoir() throws Exception {
		return niveauSavoirRepository.findAll();
	}
	
	public NiveauSavoir getNiveauSavoir(Long id) throws Exception {
		return niveauSavoirRepository.findOne(id);
	}
	
	public void deleteNiveauSavoir(Long id) {
		niveauSavoirRepository.delete(id);
	}
	
	public NiveauSavoir addNiveauSavoir(NiveauSavoir niveauSavoir) throws Exception {
		return niveauSavoirRepository.save(niveauSavoir);
	}

	public NiveauSavoir editNiveauSavoir(Long id, NiveauSavoir niveauSavoir) throws Exception {
		return niveauSavoirRepository.save(niveauSavoir);
	}

	public void clearNiveauSavoirTable() {
		niveauSavoirRepository.deleteAll();
	}

}
