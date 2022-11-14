package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping("/equipamentos")
    public ModelAndView equipamentos() {
        List<Equipamento> equipamentos = this.equipamentoRepository.findAll();

        ModelAndView mv = new ModelAndView("equipamentos");
        mv.addObject("equipamentos", equipamentos);

        return mv;
    }
}
