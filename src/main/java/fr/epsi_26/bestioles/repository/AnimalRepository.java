package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // Recherche par species
    List<Animal> findBySpecies(Species species);

    // Recherche par couleurs findByColorIn
    List<Animal> findByColorIn(List<String> colors);

    // TP5
    // nombre par sexe
    @Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :sexe")
    Long countBySexCustom(@Param("sexe") String sexe);

    // animal a un proprio ?? (sup à 0)
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE :animal MEMBER OF p.animals")
    Boolean belongsToAtLeastOnePerson(@Param("animal") Animal animal);
}