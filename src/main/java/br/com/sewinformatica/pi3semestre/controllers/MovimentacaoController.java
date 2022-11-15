package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping("movimentacoes/{id}/delete")
    public String delete(@PathVariable Integer id) {
        this.movimentacaoRepository.deleteById(id);

        return "redirect:/equipamentos";
    }
}
