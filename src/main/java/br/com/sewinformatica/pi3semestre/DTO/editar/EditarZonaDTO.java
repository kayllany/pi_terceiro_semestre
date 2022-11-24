package br.com.sewinformatica.pi3semestre.DTO.editar;

import br.com.sewinformatica.pi3semestre.models.Zona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditarZonaDTO {
    private String nome;
    private String descricao;

    public Zona toZona() {
        return new Zona(
                nome = this.nome,
                descricao = this.descricao
        );
    }

    public Zona toZona(Zona zona) {
        zona.setNome(this.nome);
        zona.setDescricao(this.descricao);

        return zona;
    }

    public void fromZona(Zona zona) {
        this.nome = zona.getNome();
        this.descricao = zona.getDescricao();
    }
}
