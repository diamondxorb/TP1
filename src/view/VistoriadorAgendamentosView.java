package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Agendamento;
import model.Vistoriador;

public class VistoriadorAgendamentosView extends JFrame {
    private JTable tabelaAgendamentos;
    private JButton btnVisualizar, btnLaudo;
    private Vistoriador vistoriador;
    private List<Agendamento> agendamentosDoDia;

    public VistoriadorAgendamentosView(Vistoriador vistoriador) {
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
        // Apenas para mostrar na tela, será feito um banco de dados
        agendamentosDoDia = new ArrayList<>();
        agendamentosDoDia.add(new Agendamento(1, new Date(), "Cliente 1", "Local 1", "Pendente"));
        agendamentosDoDia.add(new Agendamento(2, new Date(), "Cliente 2", "Local 2", "Pendente"));
        agendamentosDoDia.add(new Agendamento(3, new Date(), "Cliente 3", "Local 3", "Pendente"));
        agendamentosDoDia.add(new Agendamento(4, new Date(), "Cliente 4", "Local 4", "Pendente"));
        agendamentosDoDia.add(new Agendamento(5, new Date(), "Cliente 5", "Local 5", "Pendente"));
        
        atualizarTabela();
    }

    private void atualizarTabela() {
        Object[][] dados = new Object[agendamentosDoDia.size()][5];

        for (int i=0; i<agendamentosDoDia.size(); i++) {
            Agendamento agen = agendamentosDoDia.get(i);
            dados[i][0] = agen.getId();
            dados[i][1] = agen.getDataHoraFormatada();
            dados[i][2] = agen.getCliente();
            dados[i][3] = agen.getLocal();
            dados[i][4] = agen.getStatus();
        }

        tabelaAgendamentos.setModel(new javax.swing.table.DefaultTableModel(dados,
                new String[] {"ID", "Data/Hora", "Cliente", "Local", "Status"}
        ));
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
        Agendamento agendamento = buscarAgendamentoPorId(agendamentoId);

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
