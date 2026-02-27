package fr.epsi_26.bestioles;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.model.Species;
import fr.epsi_26.bestioles.model.Person;
import fr.epsi_26.bestioles.repository.AnimalRepository;
import fr.epsi_26.bestioles.repository.PersonRepository;
import fr.epsi_26.bestioles.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(BestiolesApplication.class, args);
    }

// AUtre maniere avec constructuer :
/*    @SpringBootApplication
    public class BestiolesApplication implements CommandLineRunner {

        private final AnimalRepository animalRepository;

        // Le constructeur qui remplace le @Autowired sur l'attribut
        public BestiolesApplication(AnimalRepository animalRepository) {
            this.animalRepository = animalRepository;
        }*/

    @Override
    public void run(String... args) throws Exception {
        System.out.println("======= GO GO GO !! =======");

        // CRUD
        // Afficher  findAll
        System.out.println("\nListe :");
        animalRepository.findAll().forEach(System.out::println);

        // Création  save
        System.out.println("\nNouvelles bestioles...");
        Species especeChat = speciesRepository.findById(1).orElse(null);
        Species especeChien = speciesRepository.findById(2).orElse(null);
        Animal a1 = new Animal("Tigrou", "Roux", "M", especeChat);
        Animal a2 = new Animal("Snoopy", "Blanc", "M", especeChien);
        animalRepository.save(a1);
        animalRepository.save(a2);
        System.out.println("Nouvelles bestioles sauvegardées !");

        // Recherche  findById
        System.out.println("\nRecherche ID " + a1.getId() + " :");
        Optional<Animal> animalTrouve = animalRepository.findById(a1.getId());
        animalTrouve.ifPresent(animal -> System.out.println("Trouvé : " + animal));

        // Supprimer  delete
        System.out.println("\nSuppression ID " + a1.getId());
        animalRepository.delete(animalTrouve.get());

        // afficher longueur
        long nombreRestants = animalRepository.count();
        System.out.println("Suppression OK. Nombre d'animaux en DB : " + nombreRestants);

        System.out.println("======= FINITO PIPO =======");

        System.out.println("\nPERSONNES");
        personRepository.findAll().forEach(person -> {
            System.out.println(person.getFirstname() + " possède " + person.getAnimals().size() + " animal/animaux !");
        });


        System.out.println("\nTP4  - TESTS SPECIES");

        // findFirstByCommonName
        System.out.println("Recherche du premier 'Chat' :");
        Species chat = speciesRepository.findFirstByCommonName("Chat");
        System.out.println(chat);

        // findByLatinNameContainingIgnoreCase
        System.out.println("\nRecherche nom latin, ex : lupus :");
        List<Species> lupusSpecies = speciesRepository.findByLatinNameContainingIgnoreCase("Lupus");
        lupusSpecies.forEach(System.out::println);
    }
}