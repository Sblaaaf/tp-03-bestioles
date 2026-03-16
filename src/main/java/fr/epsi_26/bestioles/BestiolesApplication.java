package fr.epsi_26.bestioles;

import fr.epsi_26.bestioles.model.Animal;
import fr.epsi_26.bestioles.model.Species;
import fr.epsi_26.bestioles.model.Person;
import fr.epsi_26.bestioles.repository.AnimalRepository;
import fr.epsi_26.bestioles.repository.PersonRepository;
import fr.epsi_26.bestioles.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
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


        System.out.println("\nTP4 - TESTS PERSON");

        // Recherche "Lamarque" "Jean"
        System.out.println("Recherche 'Lamarque' 'Jean' :");
        List<Person> lamarqueOuJean = personRepository.findByLastnameOrFirstname("Lamarque", "Jean");
        lamarqueOuJean.forEach(System.out::println);

        // Recherche personnes >= 50
        System.out.println("\nRecherche des vieux :");
        List<Person> seniors = personRepository.findByAgeGreaterThanEqual(50);
        seniors.forEach(System.out::println);


        System.out.println("\nTP4 - TESTS ANIMAL");

        /*// appele de especeChat
        Species especeChat = speciesRepository.findFirstByCommonName("Chat");*/

        // chats
        System.out.println("chats :");
        List<Animal> tousLesChats = animalRepository.findBySpecies(especeChat);
        tousLesChats.forEach(System.out::println);

        // couleur : Blanc ou Noir
        System.out.println("\nAnimaux Blancs ou Noirs :");
        List<String> couleursRecherchees = java.util.List.of("Blanc", "Noir");
        List<Animal> animauxBlancsOuNoirs = animalRepository.findByColorIn(couleursRecherchees);
        animauxBlancsOuNoirs.forEach(System.out::println);


        System.out.println("\nTP5 - @QUERY species");
        //nom commun ascendant
        System.out.println("species par ordre alphabétique :");
        List<Species> especesTriees = speciesRepository.findAllOrderedByCommonNameAsc();
        especesTriees.forEach(System.out::println);

        // LIKE
        // N'oublie pas les '%' pour indiquer "n'importe quoi avant et n'importe quoi après"
        System.out.println("\nspecies avec 'ha' :"); // exemple avec "ha"
        List<Species> especesLike = speciesRepository.findByCommonNameLike("%ha%"); // %pour avnt apres
        especesLike.forEach(System.out::println);


        System.out.println("\n@QUERY PERSON");
        // 20 et 40 ans
        System.out.println("Personnes entre 20 - 40 :");
        List<Person> personnesAgees = personRepository.findByAgeBetweenCustom(20, 40);
        personnesAgees.forEach(System.out::println);

        // Propriétaires d'un animal ID
        System.out.println("\n proprio de ID1 :");
        Animal max = animalRepository.findById(1).orElse(null);

        if (max != null) {
            List<Person> proprietairesDeMax = personRepository.findPersonsByAnimal(max);
            proprietairesDeMax.forEach(System.out::println);
        }


        System.out.println("\nTP5 @QUERY ANIMAL");
        // nombre de femelle
        Long nombreFemelles = animalRepository.countBySexCustom("F");
        System.out.println("Nombre femelles : " + nombreFemelles);

        // ID 1 proprio ?? id1 = max
        Animal maxAnimal = animalRepository.findById(1).orElse(null);
        if (maxAnimal != null) {
            Boolean maxAUnMaitre = animalRepository.belongsToAtLeastOnePerson(maxAnimal);
            System.out.println(maxAnimal.getName() + " a un propio ? " + (maxAUnMaitre ? "OUI" : "NON"));
        }

        System.out.println("\nTP6 TESTS REPOSITORY CUSTOM");

        // Générer des personnes aléatoires
        System.out.println("Génération de 5 personnes aléatoires...");
        personRepository.generateRandomPersons(5);
        System.out.println("Génération terminée. Nb en BDD: " + personRepository.count());

        // Supprimer les personnes sans animaux
        System.out.println("\nSuppression des personnes sans animaux...");
        personRepository.deletePersonsWithoutAnimals();
        System.out.println("Suppression terminée. Nb restant en BDD : " + personRepository.count());
    }
}