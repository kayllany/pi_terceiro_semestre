package br.com.sewinformatica.pi3semestre.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Responsible {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @NotNull
    private String user;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String role;
    @NotNull
    private String sector;
}
