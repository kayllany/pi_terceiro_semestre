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

    /**
     * Metodo para gerar um Tipo a partir da instancia dessa classe
     * @author Ryu
     * @return Tipo
     */
    public Tipo toTipo() {
        return new Tipo(
                nome = this.nome
        );
    }

    /**
     * Método para passar as informações do objeto no DTO
     * @author Ryu
     * @param {@code Tipo}
     * @return void
     */
    public void fromTipo(Tipo tipo) {
        this.nome = tipo.getNome();
    }
}
