package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TipoController {

    @GetMapping ("/tipos")
    public ModelAndView tipos() {
        ModelAndView mv = new ModelAndView("tipos");

        return mv;
    }
}
