package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.Membre;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {

}
