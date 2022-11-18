package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.EquipamentoDTO;
import br.com.sewinformatica.pi3semestre.enums.StatusEnum;
import br.com.sewinformatica.pi3semestre.models.Equipamento;
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

import java.util.List;
import java.util.Optional;

@Controller
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ZonaRepository zonaRepository;

    @GetMapping("/equipamentos")
    public ModelAndView equipamentos() {
        List<Equipamento> equipamentos = this.equipamentoRepository.findAll();
        List<Responsavel> responsaveis = this.responsavelRepository.findAll();

        ModelAndView mv = new ModelAndView("equipamento/listaEquipamento");
        mv.addObject("equipamentos", equipamentos);
        mv.addObject("responsaveis", responsaveis);

        return mv;
    }

    @GetMapping("equipamentos/{id}/view")
    public ModelAndView view(@PathVariable Integer id) {
        Optional<Equipamento> equipamento = this.equipamentoRepository.findById(id);
        List<Responsavel> responsaveis = this.responsavelRepository.findAll();
        List<Zona> zonas = this.zonaRepository.findAll();

        ModelAndView mv = new ModelAndView("equipamento/detalhesEquipamento");
        mv.addObject("responsaveis", responsaveis);
        mv.addObject("equipamento", equipamento.get());
        mv.addObject("zonas", zonas);
        mv.addObject("movimentacaoStatus", StatusEnum.values());


        return mv;
    }

    @PostMapping("/equipamentos/create")
    public String create(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoDTO.toEquipamento();
        this.equipamentoRepository.save(equipamento);

        return "redirect:/equipamentos";
    }

    @GetMapping("equipamentos/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.equipamentoRepository.deleteById(id);

        return "redirect:/equipamentos";
    }
}
