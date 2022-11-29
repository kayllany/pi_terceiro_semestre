package br.com.sewinformatica.pi3semestre.repositories;

import br.com.sewinformatica.pi3semestre.exception.ServiceExc;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Repository
public interface ResponsavelRepository extends JpaRepository <Responsavel, Integer> {

    /**
     * Método para encontrar o responsável pelo usuário
     * @author Matheus
     * @param {@code String} usuário
     * @return {@code Responsavel}
     */
    @Query("SELECT i FROM Responsavel i WHERE i.usuario = :usuario")
    public Responsavel findByUsuario(String usuario);

    /**
     * Método para encontrar o login do responsável
     * @author Matheus
     * @param {@code String} usuário, {@code String} senha
     * @return {@code Responsavel}
     */
    @Query("SELECT i FROM Responsavel i WHERE i.usuario = :usuario AND i.senha = :senha")
    public Responsavel buscarLogin(String usuario, String senha);
}
