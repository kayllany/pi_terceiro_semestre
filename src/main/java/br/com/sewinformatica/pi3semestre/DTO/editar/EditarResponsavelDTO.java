package br.com.sewinformatica.pi3semestre.DTO.editar;

import br.com.sewinformatica.pi3semestre.models.Responsavel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditarResponsavelDTO {
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

    public Responsavel toResponsavel(Responsavel responsavel){
        responsavel.setUsuario(this.usuario);
        responsavel.setSenha(this.senha);
        responsavel.setNome(this.nome);
        responsavel.setSenha(this.setor);

        return responsavel;
    }

    public void fromResponsavel(Responsavel responsavel) {
        this.usuario = responsavel.getUsuario();
        this.senha = responsavel.getSenha();
        this.nome = responsavel.getNome();
        this.setor = responsavel.getSetor();
    }
}
