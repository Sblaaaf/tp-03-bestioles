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

    private String color;
    private String name;
    private String sex;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    public Animal(String name, String color, String sex, Species species) {
        this.name = name;
        this.color = color;
        this.sex = sex;
        this.species = species;
    }

    @Override
    public String toString() {
        return "Animal{id=" + id + ", name='" + name + "', color='" + color + "', sex='" + sex + "'}";
    }
}