package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.MovimentacaoDTO;
import br.com.sewinformatica.pi3semestre.DTO.ZonaDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarZonaDTO;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.ZonaRepository;
import br.com.sewinformatica.pi3semestre.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ZonaController {

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping("/zonas")
    public ModelAndView zonas(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            List<Zona> zonas = this.zonaRepository.findAll();

            mv.setViewName("zona/listaZona");
            mv.addObject("zonas", zonas);

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("/zonas/new")
    public ModelAndView newZona(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)){
            mv.setViewName("zona/novoZona");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @PostMapping("/zonas/create")
    public ModelAndView create(ZonaDTO zonaDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)){
            Zona zona = zonaDTO.toZona();
            this.zonaRepository.save(zona);

            mv.setViewName("redirect:/zonas");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("/zonas/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            this.zonaRepository.deleteById(id);

            mv.setViewName("redirect:/zonas");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("zonas/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, ZonaDTO zonaDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Zona> optional = this.zonaRepository.findById(id);

            if (optional.isPresent()) {
                Zona zona = optional.get();
                zonaDTO.fromZona(zona);

                mv.setViewName("zona/editarZona");
                mv.addObject("zona", zonaDTO);
                mv.addObject("zonaId", zona.getId());

                return mv;

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS A ZONA ****************\n");

                mv.setViewName("redirect:/zonas");
            }

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @PostMapping("zonas/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarZonaDTO editarZonaDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Zona> optional = this.zonaRepository.findById(id);

            if (optional.isPresent()) {
                Zona zona = editarZonaDTO.toZona(optional.get());
                this.zonaRepository.save(zona);

                mv.setViewName("redirect:/zonas");

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS A ZONA ****************\n");

                mv.setViewName("redirect:/zonas");
            }

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }
}
