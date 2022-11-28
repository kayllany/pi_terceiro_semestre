package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.editar.EditarEquipamentoDTO;
import br.com.sewinformatica.pi3semestre.DTO.EquipamentoDTO;
import br.com.sewinformatica.pi3semestre.enums.StatusEnum;
import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.*;
import br.com.sewinformatica.pi3semestre.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ZonaRepository zonaRepository;
    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping("/equipamentos")
    public ModelAndView equipamentos(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)){
            List<Equipamento> equipamentos = this.equipamentoRepository.findAll();
            List<Tipo> tipos = this.tipoRepository.findAll();

            mv.setViewName("equipamento/listaEquipamento");
            mv.addObject("equipamentos", equipamentos);
            mv.addObject("tipos", tipos);

        } else {
            mv.setViewName("erro/naoLogado");
        }

        return mv;
    }

    @GetMapping("equipamentos/{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)) {
            Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

            if (optional.isPresent()) {
                Equipamento equipamento = optional.get();
                List<Responsavel> responsaveis = this.responsavelRepository.findAll();
                List<Zona> zonas = this.zonaRepository.findAll();

                mv.setViewName("equipamento/detalhesEquipamento");
                mv.addObject("equipamento", equipamento);

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }

        } else {
            mv.setViewName("erro/naoLogado");
        }
        return mv;
    }

    @GetMapping("equipamentos/new")
    public ModelAndView newEquipamento(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)) {
            List<Tipo> tipos = this.tipoRepository.findAll();

            mv.setViewName("equipamento/novoEquipamento");
            mv.addObject("tipos", tipos);
            mv.addObject("movimentacaoStatus", StatusEnum.values());

        } else {
            mv.setViewName("erro/naoLogado");
        }
        return mv;
    }

    @PostMapping("/equipamentos/create")
    public ModelAndView create(EquipamentoDTO equipamentoDTO, BindingResult bindingResult, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)) {
            if (bindingResult.hasErrors()) {
                System.out.println("\n******** TEM ERROS ***********\n");
                mv.setViewName("equipamentos/new");

            } else {
                Equipamento equipamento = equipamentoDTO.toEquipamento();
                this.equipamentoRepository.save(equipamento);

                mv.setViewName("redirect:/equipamentos");
            }
        } else {
            mv.setViewName("erro/naoLogado");
        }

        return mv;
    }

    @GetMapping("equipamentos/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            this.equipamentoRepository.deleteById(id);

            return equipamentos(session);

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    @GetMapping("equipamentos/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, EquipamentoDTO equipamentoDTO, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)) {
            Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

            if (optional.isPresent()) {
                Equipamento equipamento = optional.get();
                equipamentoDTO.fromEquipamento(equipamento);
                List<Tipo> tipos = this.tipoRepository.findAll();

                mv.setViewName("equipamento/editarEquipamento");
                mv.addObject("equipamento", equipamentoDTO);
                mv.addObject("tipos", tipos);
                mv.addObject("equipamentoId", equipamento.getId());

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }

        } else {
            mv.setViewName("erro/naoLogado");
        }

        return mv;
    }

    @PostMapping("equipamentos/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarEquipamentoDTO editarEquipamentoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioEstaLogado(session)) {
            Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

            if (optional.isPresent()) {
                Equipamento equipamento = editarEquipamentoDTO.toEquipamento(optional.get());
                this.equipamentoRepository.save(equipamento);

                mv.setViewName("redirect:/equipamentos/" + equipamento.getId() + "/view");

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }

        } else {
            mv.setViewName("erro/naoLogado");
        }

        return mv;
    }
}
