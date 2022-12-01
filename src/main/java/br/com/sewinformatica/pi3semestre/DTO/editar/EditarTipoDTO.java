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

    /**
     * Metodo para gerar um Tipo a partir da instancia dessa classe
     * @author Kevin
     * @return Tipo
     */
    public Tipo toTipo() {
        return new Tipo(
                nome = this.nome
        );
    }

    /**
     * Metodo para gerar um Tipo a partir de um outro Tipo passado como argumento
     * @author Kevin
     * @param tipo Tipo - fonte das informações para gerar um segundo tipo
     * @return Tipo
     */
    public Tipo toTipo(Tipo tipo) {
        tipo.setNome(this.nome);

        return tipo;
    }

    /**
     * Metodo para gerar um DTO a partir de um tipo passado como parametro
     * @author Kevin
     * @param tipo Tipo - Objeto do qual serão extraidas as informações do DTO
     */
    public void fromTipo(Tipo tipo) {
        this.nome = tipo.getNome();
    }
}
