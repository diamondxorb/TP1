package repository;

import model.Agendamento;
import java.util.List;

public interface AgendamentoRepository {
    void salvar(Agendamento agendamento);
    List<Agendamento> listarTodos();
    Agendamento buscarPorId(int id);
}