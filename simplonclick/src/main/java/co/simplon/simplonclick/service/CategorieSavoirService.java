package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.CategorieSavoir;
import co.simplon.simplonclick.repository.CategorieSavoirRepository;

@Service
public class CategorieSavoirService {
	
	@Autowired
	private CategorieSavoirRepository categorieSavoirRepository;
	
	public Iterable<CategorieSavoir> getAllCategoriesSavoir() throws Exception {
		return categorieSavoirRepository.findAll();
	}
	
	public CategorieSavoir getCategorieSavoir(Long id) throws Exception {
		return categorieSavoirRepository.findOne(id);
	}
	
	public void deleteCategorieSavoir(Long id) {
		categorieSavoirRepository.delete(id);
	}
	
	public CategorieSavoir addCategorieSavoir(CategorieSavoir categorieSavoir) throws Exception {
		return categorieSavoirRepository.save(categorieSavoir);
	}

	public CategorieSavoir editCategorieSavoir(Long id, CategorieSavoir categorieSavoir) throws Exception {
		return categorieSavoirRepository.save(categorieSavoir);
	}

	public void clearCategorieSavoirTable() {
		categorieSavoirRepository.deleteAll();
	}

}
