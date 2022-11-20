package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.TipoDTO;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping ("/tipos")
    public ModelAndView tipos() {
        List<Tipo> tipos = this.tipoRepository.findAll();

        ModelAndView mv = new ModelAndView("tipo/listaTipo");
        mv.addObject("tipos", tipos);

        return mv;
    }

    @PostMapping("/tipos/create")
    public String create(TipoDTO tipoDTO) {
        Tipo tipo = tipoDTO.toTipo();
        this.tipoRepository.save(tipo);

        return "redirect:/tipos";
    }

    @GetMapping("/tipos/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.tipoRepository.deleteById(id);

        return "redirect:/tipos";
    }
}
