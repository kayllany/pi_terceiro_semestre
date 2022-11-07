package br.com.sewinformatica.pi3semestre.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Equipment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    private Type type;

    @Column( nullable = false )
    private String name;
    @Column( nullable = false )
    private String description;
    private String unitOfMeasurement;
    @Column( nullable = false )
    private String SAPCode;

    public Equipment(Type type, String name, String description, String unitOfMeasurement, String SAPCode) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.unitOfMeasurement = unitOfMeasurement;
        this.SAPCode = SAPCode;
    }
}
