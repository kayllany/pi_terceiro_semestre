package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.ResponsavelDTO;
import br.com.sewinformatica.pi3semestre.enums.SetoresEnum;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @GetMapping("/responsaveis")
    public ModelAndView responsaveis() {
        List<Responsavel> responsaveis = this.responsavelRepository.findAll();

        ModelAndView mv = new ModelAndView("responsavel/listaResponsavel");
        mv.addObject("responsaveis", responsaveis);

        return mv;
    }

    @GetMapping("/responsaveis/new")
    public ModelAndView newResponsavel(ResponsavelDTO responsavelDTO) {
        ModelAndView mv = new ModelAndView("responsavel/novoResponsavel");
        mv.addObject("setores", SetoresEnum.values());

        return mv;
    }

    @PostMapping("/responsaveis/create")
    public String create(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelDTO.toReponsavel();
        this.responsavelRepository.save(responsavel);

        return "redirect:/responsaveis";
    }

    @GetMapping("/responsaveis/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.responsavelRepository.deleteById(id);

        return "redirect:/responsaveis";
    }
}
