package repository;

import model.Agendamento;
import model.Veiculo;
import model.Proprietario;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AgendamentoRepositoryMemoria implements AgendamentoRepository {
    private List<Agendamento> agendamentos = new ArrayList<>();

    // Construtor para carregar dados iniciais
    public AgendamentoRepositoryMemoria() {
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        // Criar um proprietário de exemplo
        Proprietario propExemplo = new Proprietario(
                "Fulano",
                "123.456.789-00",
                java.time.LocalDate.now(),
                "Rua Exemplo",
                "fulano@email.com",
                "(11) 99999-9999",
                "123456",
                "senha123"
        );

        // Criar um veículo de exemplo
        Veiculo veiculoExemplo = new Veiculo(
                "ABC1D23",
                "Fiat Argo",
                propExemplo,
                true
        );

        // Adicionar agendamentos de exemplo
        agendamentos.add(new Agendamento(
                1,
                new Date(), // Data atual
                "Cliente Exemplo 1",
                "Centro de Vistorias - SP",
                "Pendente",
                veiculoExemplo
        ));

        agendamentos.add(new Agendamento(
                2,
                new Date(System.currentTimeMillis() + 3600000), // 1 hora depois
                "Cliente Exemplo 2",
                "Centro de Vistorias - RJ",
                "Confirmado",
                veiculoExemplo
        ));
    }

    @Override
    public void salvar(Agendamento agendamento) {
        agendamentos.removeIf(a -> a.getId() == agendamento.getId());
        agendamentos.add(agendamento);
    }

    @Override
    public List<Agendamento> listarTodos() {
        return new ArrayList<>(agendamentos);
    }

    @Override
    public Agendamento buscarPorId(int id) {
        return agendamentos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
}