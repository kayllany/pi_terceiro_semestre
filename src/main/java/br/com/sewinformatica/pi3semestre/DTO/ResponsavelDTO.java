package br.com.sewinformatica.pi3semestre.DTO;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsavelDTO {
    private String usuario;
    private String senha;
    private String nome;
    private String setor;

    public Responsavel toReponsavel() {
        return new Responsavel(
                usuario = this.usuario,
                senha = this.senha,
                nome = this.nome,
                setor = this.setor
        );
    }

    public void fromResponsavel(Responsavel responsavel) {
        this.usuario = responsavel.getUsuario();
        this.senha = responsavel.getSenha();
        this.nome = responsavel.getNome();
        this.setor = responsavel.getSetor();
    }
}
