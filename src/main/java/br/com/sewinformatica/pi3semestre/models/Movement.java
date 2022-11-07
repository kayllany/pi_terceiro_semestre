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

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOut;

    @NotNull
    private String status;
    @NotNull
    private Integer quantity;
    private String Obs;
}
