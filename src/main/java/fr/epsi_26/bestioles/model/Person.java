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
    private Boolean active;

    // MANY-TO-MANY PERSON
    @ManyToMany(fetch = FetchType.EAGER) // EAGER permet de charger les animaux et personne
    @JoinTable(
            name = "person_animals",
            joinColumns = @JoinColumn(name = "person_id"), // pointe vers Person
            inverseJoinColumns = @JoinColumn(name = "animals_id") // pointe vers Animal
    )
    private List<Animal> animals;

    // MANY-TO-MANY ROLES
    @ManyToMany
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private java.util.List<Role> roles;

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