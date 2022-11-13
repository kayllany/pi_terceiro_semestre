package br.com.sewinformatica.pi3semestre.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Tipo {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false )
    private String nome;

    public Tipo(String nome) {
        this.nome = nome;
    }
}
