package br.com.sewinformatica.pi3semestre.controllers;

import br.com.sewinformatica.pi3semestre.DTO.MovimentacaoDTO;
import br.com.sewinformatica.pi3semestre.DTO.editar.EditarMovimentacaoDTO;
import br.com.sewinformatica.pi3semestre.enums.StatusEnum;
import br.com.sewinformatica.pi3semestre.models.Movimentacao;
import br.com.sewinformatica.pi3semestre.models.Responsavel;
import br.com.sewinformatica.pi3semestre.models.Zona;
import br.com.sewinformatica.pi3semestre.repositories.EquipamentoRepository;
import br.com.sewinformatica.pi3semestre.repositories.MovimentacaoRepository;
import br.com.sewinformatica.pi3semestre.repositories.ResponsavelRepository;
import br.com.sewinformatica.pi3semestre.repositories.ZonaRepository;
import br.com.sewinformatica.pi3semestre.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private ResponsavelService responsavelService;

    /**
     * Método para direcionar o usuario para o formulario de criação de movimentação
     * @author Kevin
     * @param equipamentoId Integer - id do equipamento a ser movimentado
     * @param session HttpSession - objeto com as informações da sessão aberta
     * @return ModelAndView - objeto com o direcionamento para a pagina de destino contendo os objetos que devem ser acessados
     */
    @GetMapping("/movimentacoes/{equipamentoId}/new")
    public ModelAndView newMovimentacao(@PathVariable Integer equipamentoId, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            List<Zona> zonas = this.zonaRepository.findAll();

            mv.setViewName("movimentacao/novoMovimentacao");
            mv.addObject("zonas", zonas);
            mv.addObject("movimentacaoStatus", StatusEnum.values());
            mv.addObject("equipamentoId", equipamentoId);

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    /**
     * Metodo para a criação do registro de movimentação
     * @author Kevin
     * @param movimentacaoDTO MovimentacaoDTO - objeto que carrega as informação para o banco de dados
     * @param id Integer - chave primaria do equipamento que esta sendo movimentado
     * @param session HttpSession - objeto com as informações da sessão aberta
     * @return ModelAndView - objeto com o direcionamento para a pagina de destino contendo os objetos que devem ser acessados
     */
    @PostMapping("/movimentacoes/{id}/create")
    public ModelAndView create(MovimentacaoDTO movimentacaoDTO, @PathVariable Integer id, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Responsavel responsavel = (Responsavel) session.getAttribute("responsavelLogado");

            movimentacaoDTO.setDataEntrada(new Date());
            movimentacaoDTO.setResponsavel(responsavel);
            movimentacaoDTO.setEquipamento(equipamentoRepository.findById(id).get());

            Movimentacao movimentacao = movimentacaoDTO.toMovimentacao();
            this.movimentacaoRepository.save(movimentacao);

            mv.setViewName("redirect:/equipamentos/" + id + "/view");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    /**
     * Metodo para deletar uma determinada movimentação
     * @author Kevin
     * @param id Integer - chave primaria da movimentação a ser excluida
     * @param session HttpSessio - objeto contendo as informações da sessão aberta
     * @return ModelAndView - objeto com o direcionamento para a pagina de destino contendo os objetos que devem ser acessados
     */
    @GetMapping("movimentacoes/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Integer equipamentoId = this.movimentacaoRepository.findById(id).get().getEquipamento().getId();
            this.movimentacaoRepository.deleteById(id);

            mv.setViewName("redirect:/equipamentos/" + equipamentoId + "/view");

        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    /**
     * Metodo para acessar o formulario de edição de uma determinada movimentação
     * @author Kevin
     * @param id Integer - chave primaria da movimentação a ser editada
     * @param editarMovimentacaoDTO EditarMovimentacaoDTO - objeto contendo as informações da movimentação editada
     * @param session HttpSessio - objeto contendo as informações da sessão aberta
     * @return ModelAndView - objeto com o direcionamento para a pagina de destino contendo os objetos que devem ser acessados
     */
    @GetMapping("movimentacoes/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, EditarMovimentacaoDTO editarMovimentacaoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Movimentacao> optional = this.movimentacaoRepository.findById(id);

            if (optional.isPresent()) {
                Movimentacao movimentacao = optional.get();
                editarMovimentacaoDTO.fromMovimentacao(movimentacao);
                List<Zona> zonas = this.zonaRepository.findAll();

                mv.setViewName("movimentacao/editarMovimentacao");
                mv.addObject("movimentacao", editarMovimentacaoDTO);
                mv.addObject("movimentacaoId", movimentacao.getId());
                mv.addObject("zonas", zonas);
                mv.addObject("movimentacaoStatus", StatusEnum.values());

                return mv;

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS A MOVIMENTAÇÃO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }
        } else {
            mv.setViewName("erro/naoPermitido");
        }

        return mv;
    }

    /**
     * Metodo para editar o registro de uma determinada movimentação no banco de dados
     * @author Kevin
     * @param id Integer - chave primaria da movimentação a ser editada
     * @param editarMovimentacaoDTO EditarMovimentacaoDTO - objeto contendo as informações da movimentação editada
     * @param session HttpSessio - objeto contendo as informações da sessão aberta
     * @return ModelAndView - objeto com o direcionamento para a pagina de destino contendo os objetos que devem ser acessados
     */
    @PostMapping("movimentacoes/{id}/update")
    public ModelAndView update(@PathVariable Integer id, EditarMovimentacaoDTO editarMovimentacaoDTO, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        if (responsavelService.usuarioTemPermissao(session)) {
            Optional<Movimentacao> optional = this.movimentacaoRepository.findById(id);

            if (optional.isPresent()) {
                Responsavel responsavel = (Responsavel) session.getAttribute("responsavelLogado");
                editarMovimentacaoDTO.setResponsavel(responsavel);
                Movimentacao movimentacao = editarMovimentacaoDTO.toMovimentacao(optional.get());
                this.movimentacaoRepository.save(movimentacao);

                mv.setViewName("redirect:/equipamentos/" + movimentacao.getEquipamento().getId() + "/view");

            } else {
                System.out.println("\n**************** NAO ENCONTRAMOS A MOVIMENTAÇÃO ****************\n");

                mv.setViewName("redirect:/equipamentos");
            }
        }

        return mv;
    }
}
