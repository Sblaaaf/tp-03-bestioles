package fr.epsi_26.bestioles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer age;
    private String firstname;
    private String lastname;
    private String login;
    private String mdp;
    private Boolean active; // Le tinyint(4) de la BDD est transformé en Boolean par Spring

    // --- LA RELATION MANY-TO-MANY ---
    @ManyToMany(fetch = FetchType.EAGER) // EAGER permet de charger les animaux en même temps que la personne
    @JoinTable(
            name = "person_animals", // Le nom exact de la table de jointure dans ta base SQL
            joinColumns = @JoinColumn(name = "person_id"), // La colonne qui pointe vers Person
            inverseJoinColumns = @JoinColumn(name = "animals_id") // La colonne qui pointe vers Animal
    )
    private List<Animal> animals;

    // On ne met pas la liste "animals" dans le toString pour éviter une boucle infinie d'affichage
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}