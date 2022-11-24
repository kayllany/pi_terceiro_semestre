package br.com.sewinformatica.pi3semestre.DTO.editar;

import br.com.sewinformatica.pi3semestre.models.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditarTipoDTO {
    private String nome;

    public Tipo toTipo() {
        return new Tipo(
                nome = this.nome
        );
    }

    public Tipo toTipo(Tipo tipo) {
        tipo.setNome(this.nome);

        return tipo;
    }

    public void fromTipo(Tipo tipo) {
        this.nome = tipo.getNome();
    }
}
