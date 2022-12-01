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
    public Zona toZona(Zona zona) {
        zona.setNome(this.nome);
        zona.setDescricao(this.descricao);

        return zona;
    }

    /**
     * Metodo para gerar um DTO a partir de uma zona passada como parametro
     * @author Kevin
     * @param zona zona - Objeto do qual serão extraidas as informações do DTO
     */
    public void fromZona(Zona zona) {
        this.nome = zona.getNome();
        this.descricao = zona.getDescricao();
    }
}
