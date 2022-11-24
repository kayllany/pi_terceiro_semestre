package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.TipoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarEquipamentoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarTipoDTO;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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

    @GetMapping ("/tipos/new")
    public ModelAndView newTipo() {
        return new ModelAndView("tipo/novoTipo");
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

    @GetMapping("tipos/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, TipoDTO tipoDTO) {
        Optional<Tipo> optional = this.tipoRepository.findById(id);

        if (optional.isPresent()) {
            Tipo tipo = optional.get();
            tipoDTO.fromTipo(tipo);

            ModelAndView mv = new ModelAndView("tipo/editarTipo");
            mv.addObject("tipo", tipoDTO);
            mv.addObject("tipoId", tipo.getId());

            return mv;

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O TIPO ****************\n");

            return new ModelAndView("redirect:/tipos");
        }
    }

    @PostMapping("tipos/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarTipoDTO editarTipoDTO) {

        Optional<Tipo> optional = this.tipoRepository.findById(id);

        if (optional.isPresent()) {
            Tipo tipo = editarTipoDTO.toTipo(optional.get());
            this.tipoRepository.save(tipo);

            return new ModelAndView("redirect:/tipos");

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

            return new ModelAndView("redirect:/equipamentos");
        }
    }
}
