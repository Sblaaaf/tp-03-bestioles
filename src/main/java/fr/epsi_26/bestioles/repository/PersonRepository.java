package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {

    // Recherche nom / prénom
    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    // Recherche par âge >=
    List<Person> findByAgeGreaterThanEqual(Integer age);

    //TP5
    // ageMin et ageMax
    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :ageMin AND :ageMax")
    List<Person> findByAgeBetweenCustom(@Param("ageMin") Integer ageMin, @Param("ageMax") Integer ageMax);

    // opérateur MEMBER OF
    @Query("SELECT p FROM Person p WHERE :animal MEMBER OF p.animals")
    List<Person> findPersonsByAnimal(@Param("animal") Animal animal);
    // @Query("SELECT p FROM Person p JOIN p.animals a WHERE a = :animal")
}