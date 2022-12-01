package br.com.sewinformatica.pi3semestre.DTO;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Movimentacao;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.models.Zona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MovimentacaoDTO {
    private Equipamento equipamento;
    private Zona zona;
    private Responsavel responsavel;
    private Date dataEntrada;
    private Date dataSaida;
    private String status;
    private Integer quantidade;
    private String obs;

    /**
     * Metodo para gerar uma Movimentacao a partir da instancia dessa classe
     * @author Kevin
     * @return Movimentacao
     */
    public Movimentacao toMovimentacao() {
        return new Movimentacao (
                equipamento = this.equipamento,
                zona = this.zona,
                responsavel = this.responsavel,
                dataEntrada = this.dataEntrada,
                status = this.status,
                quantidade = this.quantidade,
                obs = this.obs
        );
    }

    /**
     * Metodo para gerar um DTO a partir de uma movimentacao passada como parametro
     * @author Kevin
     * @param movimentacao Movimentacao - Objeto do qual serão extraidas as informações do DTO
     */
    public void fromMovimentacao(Movimentacao movimentacao) {
        this.equipamento = movimentacao.getEquipamento();
        this.zona = movimentacao.getZona();
        this.responsavel = movimentacao.getResponsavel();
        this.dataEntrada = movimentacao.getDataEntrada();
        this.status = movimentacao.getStatus();
        this.quantidade = movimentacao.getQuantidade();
        this.obs = movimentacao.getObs();
    }
}
