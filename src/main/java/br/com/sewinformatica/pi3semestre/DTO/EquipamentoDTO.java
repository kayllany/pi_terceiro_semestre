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

    public Equipamento toEquipamento() {
        return new Equipamento(
                tipo = this.tipo,
                nome = this.nome,
                descricao = this.descricao,
                unidadeDeMedida = this.unidadeDeMedida,
                codigoSAP = this.codigoSAP
        );
    }
}
