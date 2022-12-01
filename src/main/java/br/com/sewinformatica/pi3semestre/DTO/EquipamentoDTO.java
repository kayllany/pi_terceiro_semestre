package br.com.sewinformatica.pi3semestre.DTO;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipamentoDTO {
    private Tipo tipo;
    private String nome;
    private String descricao;
    private String unidadeDeMedida;
    private String codigoSAP;

    /**
     * Metodo para transformar a instancia dessa classe em um objeto do tipo Equipamento
     * @author Lucas
     * @return Equipamento - um objeto para ser inserido na tabela de equipamentos na base de dados
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
     * Método que recebe um objeto do tipo Equipamento e passa as caracteristicas para a instancia dessa classe
     * @param equipamento Equipamento - objeto com as informações do equipamento na base de dados
     */
    public void fromEquipamento(Equipamento equipamento) {
        this.tipo = equipamento.getTipo();
        this.nome = equipamento.getNome();
        this.descricao = equipamento.getDescricao();
        this.unidadeDeMedida = equipamento.getUnidadeDeMedida();
        this.codigoSAP = equipamento.getCodigoSAP();
    }
}
