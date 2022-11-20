package br.com.sewinformatica.pi3semestre.DTO;

import br.com.sewinformatica.pi3semestre.models.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TipoDTO {
    private String nome;

    public Tipo toTipo() {
        return new Tipo(
                nome = this.nome
        );
    }
}
