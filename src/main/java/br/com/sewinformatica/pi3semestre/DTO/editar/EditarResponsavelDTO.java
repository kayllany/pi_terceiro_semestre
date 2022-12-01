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

    /**
     * Metodo para gerar um Responsavel a partir da instancia dessa classe
     * @author Kevin
     * @return Responsavel
     */
    public Responsavel toReponsavel() {
        return new Responsavel(
                usuario = this.usuario,
                senha = this.senha,
                nome = this.nome,
                setor = this.setor
        );
    }

    /**
     * Metodo para gerar um Responsavel a partir de um outro Responsavel passado como argumento
     * @author Kevin
     * @param responsavel Responsavel - fonte das informações para gerar um segundo responsavel
     * @return Responsavel
     */
    public Responsavel toResponsavel(Responsavel responsavel){
        responsavel.setUsuario(this.usuario);
        responsavel.setSenha(this.senha);
        responsavel.setNome(this.nome);
        responsavel.setSetor(this.setor);

        return responsavel;
    }

    /**
     * Metodo para gerar um DTO a partir de um responsavel passado como parametro
     * @author Kevin
     * @param responsavel Responsavel - Objeto do qual serão extraidas as informações do DTO
     */
    public void fromResponsavel(Responsavel responsavel) {
        this.usuario = responsavel.getUsuario();
        this.senha = responsavel.getSenha();
        this.nome = responsavel.getNome();
        this.setor = responsavel.getSetor();
    }
}
