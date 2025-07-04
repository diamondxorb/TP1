package controller;

import model.Agendamento;
import model.Vistoriador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoController {
    private List<Agendamento> agendamentos;

    public AgendamentoController() {
        // Inicialização com dados de exemplo
        this.agendamentos = new ArrayList<>();
        // Adicione alguns agendamentos fictícios para teste
    }

    public List<Agendamento> getAgendamentosDoDia(Vistoriador vistoriador, Date data) {
        // Filtra os agendamentos do vistoriador para a data especificada
        // (Implementação simplificada - na prática, você consultaria o banco de dados)
        return agendamentos.stream()
                .filter(a -> a.getDataHora().equals(data))
                .collect(Collectors.toList());
    }

    public void atualizarAgendamento(Agendamento agendamento) {
        // Atualiza o agendamento na lista (ou banco de dados)
        agendamentos.removeIf(a -> a.getId() == agendamento.getId());
        agendamentos.add(agendamento);
    }
}
