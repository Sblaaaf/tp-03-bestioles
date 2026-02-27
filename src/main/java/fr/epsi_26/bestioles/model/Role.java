package fr.epsi_26.bestioles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String label;

    public Role(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Role{id=" + id + ", label='" + label + "'}";
    }
}