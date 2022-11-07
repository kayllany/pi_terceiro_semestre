package br.com.sewinformatica.pi3semestre.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Zone {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false )
    private String name;
    @Column(nullable = false )
    private String description;

    public Zone(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
