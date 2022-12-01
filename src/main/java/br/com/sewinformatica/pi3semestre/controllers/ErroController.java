package br.com.sewinformatica.pi3semestre.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ErroController {

    /**
     * Metodo para redirecionamento para tela de erro
     * @author Kevin
     * @param message String - mensagem a ser acessada na tela de erros
     * @return ModelAndView - objeto que direciona para tela de erro com a mensagem
     */
    @GetMapping("/erro")
    public ModelAndView erro(String message) {
        ModelAndView mv = new ModelAndView("erro");
        mv.addObject("message", message);

        return mv;
    }
}
