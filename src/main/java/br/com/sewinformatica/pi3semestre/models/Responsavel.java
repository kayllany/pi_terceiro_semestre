package br.com.sewinformatica.pi3semestre.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Responsavel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false )
    private String usuario;
    @Column(nullable = false )
    private String senha;
    @Column(nullable = false )
    private String nome;
    @Column(nullable = false )
    private String setor;

    /**
     * Metodo construtor padrão da classe
     * @author Kevin
     */
    public Responsavel(){};

    /**
     * Metodo construtor com parametros
     * @param usuario String
     * @param senha String
     * @param nome String
     * @param setor String
     */
    public Responsavel(String usuario, String senha, String nome, String setor) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.setor = setor;
    }
}
