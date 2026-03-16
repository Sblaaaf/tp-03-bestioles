package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    // EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deletePersonsWithoutAnimals() {
        // IS EMPTY pour vérifier si relation vide
        String jpql = "DELETE FROM Person p WHERE p.animals IS EMPTY";
        entityManager.createQuery(jpql).executeUpdate();
    }

    @Override
    public void generateRandomPersons(int count) {
        for (int i = 0; i < count; i++) {
            Person p = new Person();
            p.setFirstname("Prénom" + i);
            p.setLastname("Nom" + i);
            p.setAge(18 + (int) (Math.random() * 62));
            p.setLogin("login" + System.currentTimeMillis() + "_" + i);
            p.setMdp("motdepasse");
            p.setActive(true);

            entityManager.persist(p);
        }
    }
}