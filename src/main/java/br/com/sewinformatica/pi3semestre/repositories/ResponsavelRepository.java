package br.com.sewinformatica.pi3semestre.repositories;

import br.com.sewinformatica.pi3semestre.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsavelRepository extends JpaRepository <Responsavel, Integer> {

    @Query("SELECT i FROM Responsavel i WHERE i.usuario = :usuario")
    public Responsavel findByUsuario(String usuario);

    @Query("SELECT i FROM Responsavel i WHERE i.usuario = :usuario AND i.senha = :senha")
    public Responsavel buscarLogin(String usuario, String senha);
}
