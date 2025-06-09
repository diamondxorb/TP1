package repository;

import model.SolicitacaoAgendamento;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepositoryMemoria implements SolicitacaoRepository {
    private List<SolicitacaoAgendamento> solicitacoes = new ArrayList<>();

    @Override
    public void salvar(SolicitacaoAgendamento solicitacao) {
        solicitacoes.add(solicitacao);
        System.out.println("Solicitação salva: " + solicitacao);
    }

    @Override
    public List<SolicitacaoAgendamento> listarTodos() {
        return solicitacoes;
    }
}