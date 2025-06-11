package repository;

import model.SolicitacaoAgendamento;
import java.util.List;

public interface SolicitacaoRepository {
    void salvar(SolicitacaoAgendamento solicitacao);
    List<SolicitacaoAgendamento> listarTodos();
}