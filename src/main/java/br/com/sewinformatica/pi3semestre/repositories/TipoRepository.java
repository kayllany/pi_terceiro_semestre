package br.com.sewinformatica.pi3semestre.repositories;

import br.com.sewinformatica.pi3semestre.models.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository <Tipo, Integer> {
}
