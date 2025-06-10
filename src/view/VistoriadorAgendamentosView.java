package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.AgendamentoController;
import model.Agendamento;
import model.Proprietario;
import model.Vistoriador;
import model.Veiculo;

public class VistoriadorAgendamentosView extends JFrame {
    private JTable tabelaAgendamentos;
    private JButton btnVisualizar, btnLaudo;
    private Vistoriador vistoriador;
    private List<Agendamento> agendamentosDoDia = new ArrayList<>();
    private final AgendamentoController agendamentoController;

    public VistoriadorAgendamentosView(Vistoriador vistoriador, AgendamentoController controller) {
        this.agendamentoController = controller;
        this.vistoriador = vistoriador;
        setTitle("Agendamentos do Dia - " + vistoriador.getNome());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        carregarAgendamentosDoDia();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tabela de agendamentos do dia do Vistoriador
        String[] colunas = {"ID", "Data/Hora", "Cliente", "Local", "Status"};
        Object[][] dados = {};

        tabelaAgendamentos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaAgendamentos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões para visualizar os detalhes e emitir laudos
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVisualizar = new JButton("Visualizar Detalhes");
        btnLaudo = new JButton("Emitir Laudo");

        btnVisualizar.addActionListener(this::visualizarDetalhes);
        btnLaudo.addActionListener(this::emitirLaudo);

        buttonPanel.add(btnVisualizar);
        buttonPanel.add(btnLaudo);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void carregarAgendamentosDoDia() {
        agendamentosDoDia.clear();

        agendamentosDoDia = agendamentoController.listarAgendamentosDoDia(new Date());
        atualizarTabela();
    }

    private void atualizarTabela() {
        String[] colunas = {"ID", "Data/Hora", "Cliente", "Local", "Modelo", "Placa", "Status"};
        Object[][] dados = new Object[agendamentosDoDia.size()][7];

        for (int i = 0; i < agendamentosDoDia.size(); i++) {
            Agendamento agen = agendamentosDoDia.get(i);
            Veiculo veiculo = agen.getVeiculo();

            dados[i][0] = agen.getId();
            dados[i][1] = agen.getDataHoraFormatada();
            dados[i][2] = agen.getCliente();
            dados[i][3] = agen.getLocal();
            dados[i][4] = veiculo.getModelo();
            dados[i][5] = veiculo.getPlaca();
            dados[i][6] = agen.getStatus();
        }

        tabelaAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
                dados, colunas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna a tabela não editável
            }
        });
    }

    private void visualizarDetalhes(ActionEvent e) {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um agendamento primeiro!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int agendamentoId = (int) tabelaAgendamentos.getValueAt(selectedRow, 0);
        Agendamento agendamento = agendamentoController.buscarPorId(agendamentoId);

        new DetalhesAgendamentoView(agendamento).setVisible(true);
    }

    private void emitirLaudo(ActionEvent e) {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um agendamento primeiro!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int agendamentoId = (int) tabelaAgendamentos.getValueAt(selectedRow, 0);
        Agendamento agendamento = buscarAgendamentoPorId(agendamentoId);

        new EmitirLaudoView(agendamento, vistoriador).setVisible(true);
        atualizarTabela();
    }

    private Agendamento buscarAgendamentoPorId(int id) {
        return agendamentosDoDia.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
