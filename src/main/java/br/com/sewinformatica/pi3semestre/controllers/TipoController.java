package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.TipoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarTipoDTO;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.repositories.TipoRepository;
import br.com.sewinformatica.pi3semestre.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping ("/tipos")
    public ModelAndView tipos(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)){
            List<Tipo> tipos = this.tipoRepository.findAll();

            mv.setViewName("tipo/listaTipo");
            mv.addObject("tipos", tipos);

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping ("/tipos/new")
    public ModelAndView newTipo(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            mv.setViewName("tipo/novoTipo");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @PostMapping("/tipos/create")
    public ModelAndView create(TipoDTO tipoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Tipo tipo = tipoDTO.toTipo();
            this.tipoRepository.save(tipo);

            mv.setViewName("redirect:/tipos");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("/tipos/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            this.tipoRepository.deleteById(id);

            mv.setViewName("redirect:/tipos");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("tipos/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, TipoDTO tipoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Tipo> optional = this.tipoRepository.findById(id);

            if (optional.isPresent()) {
                Tipo tipo = optional.get();
                tipoDTO.fromTipo(tipo);

                mv.setViewName("tipo/editarTipo");
                mv.addObject("tipo", tipoDTO);
                mv.addObject("tipoId", tipo.getId());

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O TIPO ****************\n");

                mv.setViewName("redirect:/tipos");
            }

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @PostMapping("tipos/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarTipoDTO editarTipoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Tipo> optional = this.tipoRepository.findById(id);

            if (optional.isPresent()) {
                Tipo tipo = editarTipoDTO.toTipo(optional.get());
                this.tipoRepository.save(tipo);

                mv.setViewName("redirect:/tipos");

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }
}
