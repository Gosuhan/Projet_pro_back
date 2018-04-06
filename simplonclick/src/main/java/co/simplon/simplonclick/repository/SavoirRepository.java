package co.simplon.simplonclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.Savoir;

@Repository
public interface SavoirRepository extends JpaRepository<Savoir, Long> {

}
