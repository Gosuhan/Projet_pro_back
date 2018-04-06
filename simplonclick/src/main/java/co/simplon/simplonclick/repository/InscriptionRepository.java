package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

}
