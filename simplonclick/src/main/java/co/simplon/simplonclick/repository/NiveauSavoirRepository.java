package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.NiveauSavoir;

@Repository
public interface NiveauSavoirRepository extends JpaRepository<NiveauSavoir, Long> {

}
