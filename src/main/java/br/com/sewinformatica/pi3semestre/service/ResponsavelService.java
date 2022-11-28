package br.com.sewinformatica.pi3semestre.service;

import br.com.sewinformatica.pi3semestre.exception.CriptografiaJaExiste;
import br.com.sewinformatica.pi3semestre.exception.ServiceExc;
import br.com.sewinformatica.pi3semestre.exception.UsuarioJaExiste;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import br.com.sewinformatica.pi3semestre.util.Util;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class ResponsavelService {

    @Autowired
    ResponsavelRepository responsavelRepository;

    public void salvarResponsavel(Responsavel responsavel) throws Exception {

        try {
            if(responsavelRepository.findByUsuario(responsavel.getUsuario()) != null) {
                throw new UsuarioJaExiste("Este usuário ja está cadastrado");
            }

            responsavel.setSenha(Util.md5(responsavel.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptografiaJaExiste("Erro na criptografia da senha");
        }

        responsavelRepository.save(responsavel);
    }

    public void atualizarResponsavel(Responsavel responsavel) throws Exception {

        try {
            responsavel.setSenha(Util.md5(responsavel.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptografiaJaExiste("Erro na criptografia da senha");
        }

        responsavelRepository.save(responsavel);
    }

    public Responsavel loginResponsavel(String usuario, String senha) throws ServiceExc {

        return responsavelRepository.buscarLogin(usuario, senha);
    }

    public Boolean usuarioEstaLogado(HttpSession session) {
        Responsavel responsavelLogado = (Responsavel) session.getAttribute("responsavelLogado");

        return responsavelLogado != null;
    }

    public Boolean usuarioTemPermissao(HttpSession session) {
        Responsavel responsavelLogado = (Responsavel) session.getAttribute("responsavelLogado");
        Boolean estaLogado = this.usuarioEstaLogado(session);
        boolean temPermissao;

        if (estaLogado) {
            temPermissao = Objects.equals(responsavelLogado.getSetor(), "LABORATORIO");
        } else {
            temPermissao = false;
        }

        return estaLogado && temPermissao;
    }
}
