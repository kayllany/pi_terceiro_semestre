package br.com.sewinformatica.pi3semestre.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Zona {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false )
    private String nome;
    @Column(nullable = false )
    private String descricao;


    /**
     * Metodo construtor padrão da classe
     * @author Kevin
     */
    public Zona(){};

    /**
     * Metodo construtor com parametro
     * @param nome String
     * @param descricao String
     */
    public Zona(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
