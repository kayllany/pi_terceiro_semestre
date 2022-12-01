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

    /**
     * Metodo construtor padrão da classe
     * @author Kevin
     */
    public Tipo(){};

    /**
     * Metodo construtor com parametro
     * @author Kevin
     * @param nome String
     */
    public Tipo(String nome) {
        this.nome = nome;
    }
}
