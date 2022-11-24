package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.ResponsavelDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarEquipamentoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarResponsavelDTO;
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
import java.util.Optional;

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

    @GetMapping("/responsaveis/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, ResponsavelDTO responsavelDTO) {
        Optional<Responsavel> optional = this.responsavelRepository.findById(id);

        if (optional.isPresent()) {
            Responsavel responsavel = optional.get();
            responsavelDTO.fromResponsavel(responsavel);

            ModelAndView mv = new ModelAndView("responsavel/editarResponsavel");
            mv.addObject("responsavel", responsavelDTO);
            mv.addObject("setores", SetoresEnum.values());
            mv.addObject("responsavelId", responsavel.getId());

            return mv;

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O RESPONSAVEL ****************\n");

            return new ModelAndView("redirect:/responsaveis");
        }
    }

    @PostMapping("responsaveis/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarResponsavelDTO editarResponsavelDTO) {

        Optional<Responsavel> optional = this.responsavelRepository.findById(id);

        if (optional.isPresent()) {
            Responsavel responsavel = editarResponsavelDTO.toResponsavel(optional.get());
            this.responsavelRepository.save(responsavel);

            return new ModelAndView("redirect:/responsaveis");

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O RESPONSAVEL ****************\n");

            return new ModelAndView("redirect:/responsaveis");
        }
    }
}
