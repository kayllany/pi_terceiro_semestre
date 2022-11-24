package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.ZonaDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarZonaDTO;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ZonaController {

    @Autowired
    private ZonaRepository zonaRepository;

    @GetMapping("/zonas")
    public ModelAndView zonas() {
        List<Zona> zonas = this.zonaRepository.findAll();

        ModelAndView mv = new ModelAndView("zona/listaZona");
        mv.addObject("zonas", zonas);

        return mv;
    }

    @GetMapping("/zonas/new")
    public ModelAndView newZona() {
        return new ModelAndView("zona/novoZona");
    }

    @PostMapping("/zonas/create")
    public String create(ZonaDTO zonaDTO) {
        Zona zona = zonaDTO.toZona();
        this.zonaRepository.save(zona);

        return "redirect:/zonas";
    }


    @GetMapping("/zonas/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.zonaRepository.deleteById(id);

        return "redirect:/zonas";
    }

    @GetMapping("zonas/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, ZonaDTO zonaDTO) {
        Optional<Zona> optional = this.zonaRepository.findById(id);

        if (optional.isPresent()) {
            Zona zona = optional.get();
            zonaDTO.fromZona(zona);

            ModelAndView mv = new ModelAndView("zona/editarZona");
            mv.addObject("zona", zonaDTO);
            mv.addObject("zonaId", zona.getId());

            return mv;

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS A ZONA ****************\n");

            return new ModelAndView("redirect:/zonas");
        }
    }

    @PostMapping("zonas/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarZonaDTO editarZonaDTO) {

        Optional<Zona> optional = this.zonaRepository.findById(id);

        if (optional.isPresent()) {
            Zona zona = editarZonaDTO.toZona(optional.get());
            this.zonaRepository.save(zona);

            return new ModelAndView("redirect:/zonas");

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS A ZONA ****************\n");

            return new ModelAndView("redirect:/zonas");
        }
    }
}
