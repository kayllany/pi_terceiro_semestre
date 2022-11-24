package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.editar.EditarEquipamentoDTO;
import br.com.sewinformatica.pi3semestre.DTO.EquipamentoDTO;
import br.com.sewinformatica.pi3semestre.enums.StatusEnum;
import br.com.sewinformatica.pi3semestre.models.Equipamento;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.models.Tipo;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/equipamentos")
    public ModelAndView equipamentos() {
        List<Equipamento> equipamentos = this.equipamentoRepository.findAll();
        List<Tipo> tipos = this.tipoRepository.findAll();

        ModelAndView mv = new ModelAndView("equipamento/listaEquipamento");
        mv.addObject("equipamentos", equipamentos);
        mv.addObject("tipos", tipos);

        return mv;
    }

    @GetMapping("equipamentos/{id}/view")
    public ModelAndView view(@PathVariable Integer id) {
        Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

        if (optional.isPresent()) {
            Equipamento equipamento = optional.get();
            List<Responsavel> responsaveis = this.responsavelRepository.findAll();
            List<Zona> zonas = this.zonaRepository.findAll();

            ModelAndView mv = new ModelAndView("equipamento/detalhesEquipamento");
            mv.addObject("equipamento", equipamento);

            return mv;

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

            return new ModelAndView("redirect:/equipamentos");
        }
    }

    @GetMapping("equipamentos/new")
    public ModelAndView newEquipamento() {
        List<Tipo> tipos = this.tipoRepository.findAll();

        ModelAndView mv = new ModelAndView("equipamento/novoEquipamento");
        mv.addObject("tipos", tipos);
        mv.addObject("movimentacaoStatus", StatusEnum.values());

        return mv;
    }

    @PostMapping("/equipamentos/create")
    public ModelAndView create(EquipamentoDTO equipamentoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { System.out.println("\n******** TEM ERROS ***********\n");
            return new ModelAndView("equipamentos/new");

        } else {
            Equipamento equipamento = equipamentoDTO.toEquipamento();
            this.equipamentoRepository.save(equipamento);

            return new ModelAndView("redirect:/equipamentos");
        }
    }

    @GetMapping("equipamentos/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.equipamentoRepository.deleteById(id);

        return "redirect:/equipamentos";
    }

    @GetMapping("equipamentos/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, EquipamentoDTO equipamentoDTO) {
        Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

        if (optional.isPresent()) {
            Equipamento equipamento = optional.get();
            equipamentoDTO.fromEquipamento(equipamento);
            List<Tipo> tipos = this.tipoRepository.findAll();

            ModelAndView mv = new ModelAndView("equipamento/editarEquipamento");
            mv.addObject("equipamento", equipamentoDTO);
            mv.addObject("tipos", tipos);
            mv.addObject("equipamentoId", equipamento.getId());

            return mv;

        } else {
            System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

            return new ModelAndView("redirect:/equipamentos");
        }
    }

    @PostMapping("equipamentos/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarEquipamentoDTO editarEquipamentoDTO) {

            Optional<Equipamento> optional = this.equipamentoRepository.findById(id);

            if (optional.isPresent()) {
                Equipamento equipamento = editarEquipamentoDTO.toEquipamento(optional.get());
                this.equipamentoRepository.save(equipamento);

                return new ModelAndView("redirect:/equipamentos/" + equipamento.getId() + "/view");

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS O EQUIPAMENTO ****************\n");

                return new ModelAndView("redirect:/equipamentos");
            }
    }
}
