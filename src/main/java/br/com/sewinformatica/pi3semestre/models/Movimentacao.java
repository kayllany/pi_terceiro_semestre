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
    @JoinColumn(name = "responsavel_id", nullable = false)
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

    /**
     * Metodo construtor padrão da classe
     * @author Kevin
     */
    public Movimentacao(){};

    /**
     * Metodo construtor com parametros
     * @author Kevin
     * @param equipamento Equipamento
     * @param zona Zona
     * @param responsavel Responsavel
     * @param dataEntrada Date
     * @param status String
     * @param quantidade Integer
     * @param obs String
     */
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
