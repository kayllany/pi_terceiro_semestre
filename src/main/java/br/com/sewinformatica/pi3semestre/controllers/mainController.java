package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

    /**
     * Metodo que direciona para a tela de login
     * @author Kevin
     * @return ModelAndView - objeto com o direcionamento da view de login
     */
    @GetMapping("/")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("login");

        return mv;
    }
}
