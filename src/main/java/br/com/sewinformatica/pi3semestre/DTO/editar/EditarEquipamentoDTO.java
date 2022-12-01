package br.com.sewinformatica.pi3semestre.DTO.editar;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditarEquipamentoDTO {
    private Tipo tipo;
    private String nome;
    private String descricao;
    private String unidadeDeMedida;
    private String codigoSAP;

    /**
     * Metodo para gerar um Equipamento a partir da instancia dessa classe
     * @author Kevin
     * @return Equipamento
     */
    public Equipamento toEquipamento() {
        return new Equipamento(
                tipo = this.tipo,
                nome = this.nome,
                descricao = this.descricao,
                unidadeDeMedida = this.unidadeDeMedida,
                codigoSAP = this.codigoSAP
        );
    }

    /**
     * Metodo para gerar um Equipamento a partir de um outro Equipamento passado como argumento
     * @author Kevin
     * @param equipamento  Equipamento - fonte das informações para gerar um segundo equipamento
     * @return Equipamento
     */
    public Equipamento toEquipamento( Equipamento equipamento) {
        equipamento.setTipo(this.tipo);
        equipamento.setNome(this.nome);
        equipamento.setDescricao(this.descricao);
        equipamento.setUnidadeDeMedida(this.unidadeDeMedida);
        equipamento.setCodigoSAP(this.codigoSAP);

        return equipamento;
    }

    /**
     * Metodo para gerar um DTO a partir de um equipamento passado como parametro
     * @author Kevin
     * @param equipamento Equipamento - Objeto do qual serão extraidas as informações do DTO
     */
    public void fromEquipamento(Equipamento equipamento) {
        this.tipo = equipamento.getTipo();
        this.nome = equipamento.getNome();
        this.descricao = equipamento.getDescricao();
        this.unidadeDeMedida = equipamento.getUnidadeDeMedida();
        this.codigoSAP = equipamento.getCodigoSAP();
    }
}
