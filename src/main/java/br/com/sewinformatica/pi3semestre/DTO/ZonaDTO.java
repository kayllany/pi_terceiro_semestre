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

    /**
     * Metodo para gerar uma Zona a partir da instancia dessa classe
     * @author Kevin
     * @return Zona
     */
    public Zona toZona() {
        return new Zona(
                nome = this.nome,
                descricao = this.descricao
        );
    }

    /**
     * Metodo para gerar uma Zona a partir de uma outra Zona passada como argumento
     * @author Kevin
     * @param zona  Zona - fonte das informações para gerar uma segunda zona
     * @return zona
     */
    public void fromZona(Zona zona) {
        this.nome = zona.getNome();
        this.descricao = zona.getDescricao();
    }
}
