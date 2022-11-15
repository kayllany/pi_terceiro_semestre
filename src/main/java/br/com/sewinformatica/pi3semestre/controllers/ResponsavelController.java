package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @GetMapping("/responsaveis")
    public ModelAndView responsaveis() {
        List<Responsavel> responsaveis = this.responsavelRepository.findAll();

        ModelAndView mv = new ModelAndView("responsaveis");
        mv.addObject("responsaveis", responsaveis);

        return mv;
    }
}
