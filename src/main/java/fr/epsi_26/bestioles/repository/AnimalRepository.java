package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // Recherche par species
    List<Animal> findBySpecies(Species species);

    // Recherche par couleurs findByColorIn
    List<Animal> findByColorIn(List<String> colors);
}