package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.ResponsavelDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarEquipamentoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarResponsavelDTO;
import br.com.sewinformatica.pi3semestre.enums.SetoresEnum;
import br.com.sewinformatica.pi3semestre.exception.ServiceExc;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.repositories.EquipamentoRepository;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import br.com.sewinformatica.pi3semestre.service.ResponsavelService;
import br.com.sewinformatica.pi3semestre.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Controller
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ResponsavelService responsavelService;

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
    public String create(ResponsavelDTO responsavelDTO) throws Exception {
        Responsavel responsavel = responsavelDTO.toReponsavel();
        responsavelService.salvarResponsavel(responsavel);

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
    public ModelAndView update(@PathVariable Integer id, EditarResponsavelDTO editarResponsavelDTO) throws Exception {

        Optional<Responsavel> optional = this.responsavelRepository.findById(id);

        if (optional.isPresent()) {
            Responsavel responsavel = editarResponsavelDTO.toResponsavel(optional.get());
            responsavelService.atualizarResponsavel(responsavel);

            return new ModelAndView("redirect:/responsaveis");

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O RESPONSAVEL ****************\n");

            return new ModelAndView("redirect:/responsaveis");
        }
    }

    @PostMapping("/login")
    public String login(@Validated Responsavel responsavel, BindingResult bindingResult, HttpSession session, HttpServletRequest request) throws NoSuchAlgorithmException, ServiceExc {
        request.setAttribute("usuario", new Responsavel());

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        Responsavel responsavelLogin = responsavelService.loginResponsavel(responsavel.getUsuario(), Util.md5(responsavel.getSenha()));
        if (responsavelLogin == null) {
            request.setAttribute("msg", "Usuario ou senha incorretos");
            return "login";

        } else {
            session.setAttribute("responsavelLogado", responsavelLogin);
            return "redirect:/equipamentos";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
