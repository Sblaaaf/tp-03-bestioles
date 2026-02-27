package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    // Recherche nom / prénom
    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    // Recherche par âge >=
    List<Person> findByAgeGreaterThanEqual(Integer age);
}