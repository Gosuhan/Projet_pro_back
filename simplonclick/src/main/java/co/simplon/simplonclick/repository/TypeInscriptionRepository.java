package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.TypeInscription;

@Repository
public interface TypeInscriptionRepository extends JpaRepository<TypeInscription, Long> {

}
