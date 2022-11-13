package br.com.sewinformatica.pi3semestre.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Equipamento {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    private Tipo tipo;

    @Column( nullable = false )
    private String nome;
    @Column( nullable = false )
    private String descricao;
    private String unidadeDeMedida;
    @Column( nullable = false )
    private String codigoSAP;

    public Equipamento(){};
    public Equipamento(Tipo tipo, String nome, String descricao, String unidadeDeMedida, String codigoSAP) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.unidadeDeMedida = unidadeDeMedida;
        this.codigoSAP = codigoSAP;
    }
}
