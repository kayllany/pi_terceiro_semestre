package br.com.sewinformatica.pi3semestre.DTO;

import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.models.Zona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZonaDTO {
    private String nome;
    private String descricao;

    public Zona toZona() {
        return new Zona(
                nome = this.nome,
                descricao = this.descricao
        );
    }

    public void fromZona(Zona zona) {
        this.nome = zona.getNome();
        this.descricao = zona.getDescricao();
    }
}
