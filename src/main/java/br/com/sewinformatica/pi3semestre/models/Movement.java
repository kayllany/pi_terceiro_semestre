package br.com.sewinformatica.pi3semestre.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    private Equipment equipment;
    @ManyToOne
    private Zone zone;
    @ManyToOne
    private Responsible responsible;

    @Column( nullable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOut;

    @Column( nullable = false )
    private String status;
    @Column( nullable = false )
    private Integer quantity;
    private String Obs;
}
