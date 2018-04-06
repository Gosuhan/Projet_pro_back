package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.Ressource;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {

}
