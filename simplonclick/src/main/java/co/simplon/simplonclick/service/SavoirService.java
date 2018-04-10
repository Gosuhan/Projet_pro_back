package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.repository.SavoirRepository;


@Service
public class SavoirService {
	
	@Autowired
	private SavoirRepository savoirRepository;
	
	public Iterable<Savoir> getAllSavoirs() throws Exception {
		return savoirRepository.findAll();
	}
	
	public Savoir getSavoir(Long id) throws Exception {
		return savoirRepository.findOne(id);
	}
	
	public void deleteSavoir(Long id) {
		savoirRepository.delete(id);
	}
	
	public Savoir addSavoir(Savoir savoir) throws Exception {
		return savoirRepository.save(savoir);
	}

	public Savoir editSavoir(Long id, Savoir savoir) throws Exception {
		return savoirRepository.save(savoir);
	}

	public void clearSavoirTable() {
		savoirRepository.deleteAll();
	}

}
