package br.com.sewinformatica.pi3semestre.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    private Equipamento equipamento;
    @ManyToOne
    private Zona zona;
    @ManyToOne
    private Responsavel responsavel;

    @Column( nullable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;

    @Column( nullable = false )
    private String status;
    @Column( nullable = false )
    private Integer quantidade;
    private String obs;

    public Movimentacao(Equipamento equipamento, Zona zona, Responsavel responsavel, Date dataEntrada, String status, Integer quantidade, String obs) {
        this.equipamento = equipamento;
        this.zona = zona;
        this.responsavel = responsavel;
        this.dataEntrada = dataEntrada;
        this.status = status;
        this.quantidade = quantidade;
        this.obs = obs;
    }
}
