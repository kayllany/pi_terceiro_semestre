package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.MovimentacaoDTO;
import br.com.sewinformatica.pi3semestre.models.Movimentacao;
import br.com.sewinformatica.pi3semestre.repositories.EquipamentoRepository;
import br.com.sewinformatica.pi3semestre.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @PostMapping("/movimentacoes/{id}/create")
    public String create(MovimentacaoDTO movimentacaoDTO, @PathVariable Integer id) {
        movimentacaoDTO.setDataEntrada(new Date());
        movimentacaoDTO.setEquipamento(equipamentoRepository.findById(id).get());

        Movimentacao movimentacao = movimentacaoDTO.toMovimentacao();
        this.movimentacaoRepository.save(movimentacao);

        return "redirect:/equipamentos/" + id + "/view";
    }

    @GetMapping("movimentacoes/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Integer equipamentoId = this.movimentacaoRepository.findById(id).get().getEquipamento().getId();
        this.movimentacaoRepository.deleteById(id);

        return "redirect:/equipamentos/" + equipamentoId + "/view";
    }
}
