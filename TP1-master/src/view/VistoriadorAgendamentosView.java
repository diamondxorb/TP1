package view;

import controller.AgendamentoController;
import model.Agendamento;
import util.EstiloUtil;
import util.FundoGradienteUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class VistoriadorAgendamentosView extends JFrame {
    private JTable tabelaAgendamentos;
    private String nome;

    public VistoriadorAgendamentosView(String nome) throws SQLException {
        this.nome = nome;
        setTitle("Agendamentos do Dia - " + nome);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Configuração das cores e estilo
        setContentPane(new FundoGradienteUtil());
        setLayout(new BorderLayout());
        EstiloUtil.aplicarEstilo(this);

        initComponents();
        setVisible(true);
    }

    List<Agendamento> lista = AgendamentoController.listarAgendamentosPorVistoriador(nome);

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setOpaque(false);

        // Painel de botões para visualizar os detalhes e emitir laudos
        tabelaAgendamentos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaAgendamentos);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVisualizar = new JButton("Visualizar Detalhes");
        JButton btnLaudo = new JButton("Emitir Laudo");
        JButton btnAtualizar = new JButton("Atualizar");

        btnVisualizar.addActionListener(this::visualizarDetalhes);
        btnLaudo.addActionListener(this::emitirLaudo);
        btnAtualizar.addActionListener(e->carregarDados(nome));

        buttonPanel.add(btnVisualizar);
        buttonPanel.add(btnLaudo);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        carregarDados(nome);
        add(mainPanel);
    }

    private void carregarDados(String nome) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Data", "Horário", "Motivo", "Atendente"});
        for (Agendamento ag : lista) {
            model.addRow(new Object[]{
                    ag.getId(),
                    ag.getData(),
                    ag.getHorario(),
                    ag.getMotivoAgendamento(),
                    ag.getAtendente().getNome()
            });
        }
        tabelaAgendamentos.setModel(model);
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

        new EmitirLaudoView(agendamento, nome).setVisible(true);
    }

    private Agendamento buscarAgendamentoPorId(int id) {
        return lista.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

}
