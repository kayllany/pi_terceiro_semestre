package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ErroController {

    @GetMapping("/erro")
    public ModelAndView erro(String message) {
        ModelAndView mv = new ModelAndView("erro");
        mv.addObject("message", message);

        return mv;
    }
}
