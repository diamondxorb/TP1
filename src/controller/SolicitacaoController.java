package controller;

import model.SolicitacaoAgendamento;
import repository.SolicitacaoRepository;

import java.util.List;

public class SolicitacaoController {
    private SolicitacaoRepository repositorio;

    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void solicitarAgendamento(SolicitacaoAgendamento solicitacao) {
        repositorio.salvar(solicitacao);
    }

    public List<SolicitacaoAgendamento> listarSolicitacoes() {
        return repositorio.listarTodos();
    }
}