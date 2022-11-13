package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EquipamentoController {

    @GetMapping("/equipamentos")
    public ModelAndView equipamentos() {
        ModelAndView mv = new ModelAndView("equipamentos");

        return mv;
    }
}
