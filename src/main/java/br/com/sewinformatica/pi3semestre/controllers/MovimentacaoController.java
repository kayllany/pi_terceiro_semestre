package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.MovimentacaoDTO;
import br.com.sewinformatica.pi3semestre.enums.StatusEnum;
import br.com.sewinformatica.pi3semestre.models.Movimentacao;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.EquipamentoRepository;
import br.com.sewinformatica.pi3semestre.repositories.MovimentacaoRepository;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import br.com.sewinformatica.pi3semestre.repositories.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ZonaRepository zonaRepository;

    @GetMapping("/movimentacoes/{equipamentoId}/new")
    public ModelAndView newMovimentacao(@PathVariable Integer equipamentoId) {
        List<Responsavel> responsaveis = this.responsavelRepository.findAll();
        List<Zona> zonas = this.zonaRepository.findAll();

        ModelAndView mv = new ModelAndView("movimentacao/novoMovimentacao");
        mv.addObject("responsaveis", responsaveis);
        mv.addObject("zonas", zonas);
        mv.addObject("movimentacaoStatus", StatusEnum.values());
        mv.addObject("equipamentoId", equipamentoId);

        return mv;
    }

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
