package fr.epsi_26.bestioles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    // Constructeur avec paramètres
    public Animal(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Animal{id=" + id + ", nom='" + nom + "'}";
    }
}