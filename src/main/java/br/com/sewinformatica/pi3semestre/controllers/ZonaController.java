package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ZonaController {

    @GetMapping("/zonas")
    public ModelAndView zonas() {
        ModelAndView mv = new ModelAndView("zonas");

        return mv;
    }
}
