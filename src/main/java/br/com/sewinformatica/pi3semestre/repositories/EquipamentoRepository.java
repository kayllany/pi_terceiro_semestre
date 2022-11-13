package br.com.sewinformatica.pi3semestre.repositories;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository <Equipamento, Integer> {
}
