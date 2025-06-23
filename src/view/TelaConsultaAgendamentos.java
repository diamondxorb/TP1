package view;

import controller.AgendamentoController;
import model.Agendamento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaConsultaAgendamentos extends JFrame {

    public TelaConsultaAgendamentos() {
        setTitle("Consulta de Agendamentos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTable tabela = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> carregarDados(tabela));
        panel.add(btnAtualizar, BorderLayout.SOUTH);

        carregarDados(tabela);

        add(panel);
    }

    private void carregarDados(JTable tabela) {
        try {
            List<Agendamento> lista = AgendamentoController.listarAgendamentos();
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

            tabela.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
