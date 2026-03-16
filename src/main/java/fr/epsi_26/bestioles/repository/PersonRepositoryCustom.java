package fr.epsi_26.bestioles.repository;

public interface PersonRepositoryCustom {
    // Supprimer les personnes qui n'ont pas d'animaux
    void deletePersonsWithoutAnimals();

    // Générer les entités Person
    void generateRandomPersons(int count);
}