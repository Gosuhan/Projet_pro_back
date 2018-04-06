package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.CategorieSavoir;

@Repository
public interface CategorieSavoirRepository extends JpaRepository<CategorieSavoir, Long> {

}
