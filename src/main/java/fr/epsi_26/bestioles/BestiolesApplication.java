package fr.epsi_26.bestioles;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

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
        Animal a1 = new Animal("Tigrou", "Roux", "M", 1);
        Animal a2 = new Animal("Snoopy", "Blanc", "M", 2);
        animalRepository.save(a1);
        animalRepository.save(a2);
        System.out.println("Nouvelles bestioles sauvegardées !");

        // Recherche  findById
        System.out.println("\nRecherche ID " + a1.getId() + " :");
        Optional<Animal> animalTrouve = animalRepository.findById(a1.getId());
        animalTrouve.ifPresent(animal -> System.out.println("Trouvé : " + animal));

        // Supprimer  delete, et afficher la longueur de la liste
        System.out.println("\nSuppression ID " + a1.getId());
        animalRepository.delete(animalTrouve.get());

        // afficher longueur
        long nombreRestants = animalRepository.count();
        System.out.println("Suppression OK. Nombre d'animaux en DB : " + nombreRestants);

        System.out.println("======= FINITO PIPO =======");
    }
}