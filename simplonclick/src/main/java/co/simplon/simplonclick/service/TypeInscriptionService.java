package co.simplon.simplonclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.simplonclick.model.TypeInscription;
import co.simplon.simplonclick.repository.TypeInscriptionRepository;


@Service
public class TypeInscriptionService {
	
	@Autowired
	private TypeInscriptionRepository typeInscriptionRepository;
	
	public Iterable<TypeInscription> getAllTypesInscription() throws Exception {
		return typeInscriptionRepository.findAll();
	}
	
	public TypeInscription getTypeInscription(Long id) throws Exception {
		return typeInscriptionRepository.findOne(id);
	}
	
	public void deleteTypeInscription(Long id) {
		typeInscriptionRepository.delete(id);
	}
	
	public TypeInscription addTypeInscription(TypeInscription typeInscription) throws Exception {
		return typeInscriptionRepository.save(typeInscription);
	}

	public TypeInscription editTypeInscription(Long id, TypeInscription typeInscription) throws Exception {
		return typeInscriptionRepository.save(typeInscription);
	}

	public void clearTypeInscriptionTable() {
		typeInscriptionRepository.deleteAll();
	}

}
