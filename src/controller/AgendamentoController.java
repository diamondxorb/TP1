package controller;

import model.Agendamento;
import repository.AgendamentoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoController {
    private final AgendamentoRepository repository;

    public AgendamentoController(AgendamentoRepository repository) {
        this.repository = repository;
    }

    // Métodos básicos (CRUD)
    public void criarAgendamento(Agendamento agendamento) {
        repository.salvar(agendamento);
    }

    public List<Agendamento> listarAgendamentosDoDia(Date data) {
        return repository.listarTodos().stream()
                .filter(a -> isMesmoDia(a.getDataHora(), data)) // Filtra por dia, não por hora exata
                .collect(Collectors.toList());
    }

    private boolean isMesmoDia(Date data1, Date data2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(data1).equals(sdf.format(data2));
    }

    public Agendamento buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    // Atualiza status do agendamento (ex: "Confirmado", "Cancelado")
    public void atualizarStatus(int id, String status) {
        Agendamento agendamento = repository.buscarPorId(id);
        if (agendamento != null) {
            agendamento.setStatus(status);
            repository.salvar(agendamento);
        }
    }
}