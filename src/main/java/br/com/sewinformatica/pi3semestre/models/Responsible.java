package br.com.sewinformatica.pi3semestre.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Responsible {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false )
    private String user;
    @Column(nullable = false )
    private String password;
    @Column(nullable = false )
    private String name;
    @Column(nullable = false )
    private String role;
    @Column(nullable = false )
    private String sector;
}
