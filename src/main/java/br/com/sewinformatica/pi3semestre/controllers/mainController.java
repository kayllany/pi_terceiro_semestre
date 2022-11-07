package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

    @GetMapping("/")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("main");

        return mv;
    }
}
