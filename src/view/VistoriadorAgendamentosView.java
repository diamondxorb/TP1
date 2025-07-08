package view;

import controller.AgendamentoController;
import controller.PessoaController;
import model.Agendamento;
import model.Laudo;
import model.Vistoriador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import util.EstiloUtil;
import util.FundoGradienteUtil;

public class VistoriadorAgendamentosView extends JFrame {
    private DefaultTableModel tableModel;
    private Vistoriador vistoriador;

    public VistoriadorAgendamentosView(Vistoriador vistoriador) {
        this.vistoriador = vistoriador;

        setTitle("Agendamentos para Vistoria - " + vistoriador.getNome());
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração de estilo
        setContentPane(new FundoGradienteUtil());
        setLayout(new BorderLayout());
        EstiloUtil.aplicarEstilo(this);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Tabela de agendamentos
        String[] columns = {"ID", "Data", "Horário", "Veículo", "Proprietário"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Agendamentos Aprovados"));

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);

        JButton btnEmitirLaudo = new JButton("Emitir Laudo");
        JButton btnAtualizar = new JButton("Atualizar");

        // Estilização dos botões
        btnEmitirLaudo.setBackground(EstiloUtil.AMARELO);
        btnEmitirLaudo.setForeground(EstiloUtil.PRETO);
        btnEmitirLaudo.setPreferredSize(new Dimension(150, 35));

        btnAtualizar.setBackground(EstiloUtil.CINZA_CLARO);
        btnAtualizar.setPreferredSize(new Dimension(120, 35));

        btnEmitirLaudo.addActionListener(e -> emitirLaudo(table.getSelectedRow()));
        btnAtualizar.addActionListener(e -> carregarAgendamentos());

        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnEmitirLaudo);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        carregarAgendamentos();
    }

    private void carregarAgendamentos() {
        try {
            tableModel.setRowCount(0); // Limpa a tabela

            List<Agendamento> agendamentos = AgendamentoController.listarAgendamentosPorVistoriador(vistoriador.getId());

            for (Agendamento ag : agendamentos) {
                tableModel.addRow(new Object[]{
                        ag.getId(),
                        ag.getData(),
                        ag.getHorario(),
                        ag.getVeiculo().getPlaca() + " - " + ag.getVeiculo().getModelo(),
                        ag.getVeiculo().getProprietario().getNome()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar agendamentos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emitirLaudo(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um agendamento primeiro!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idAgendamento = (int) tableModel.getValueAt(selectedRow, 0);

        // Painel de diálogo para emissão de laudo
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(400, 300));

        // Grupo de status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup bgStatus = new ButtonGroup();
        JRadioButton rbAprovado = new JRadioButton("Aprovado", true);
        JRadioButton rbReprovado = new JRadioButton("Reprovado");
        bgStatus.add(rbAprovado);
        bgStatus.add(rbReprovado);
        statusPanel.add(new JLabel("Status:"));
        statusPanel.add(rbAprovado);
        statusPanel.add(rbReprovado);

        // Área de motivo
        JTextArea taMotivo = new JTextArea(8, 30);
        taMotivo.setLineWrap(true);
        JScrollPane scrollMotivo = new JScrollPane(taMotivo);

        panel.add(statusPanel, BorderLayout.NORTH);
        panel.add(new JLabel("Motivo:"), BorderLayout.CENTER);
        panel.add(scrollMotivo, BorderLayout.SOUTH);

        int option = JOptionPane.showConfirmDialog(this, panel, "Emitir Laudo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String status = rbAprovado.isSelected() ? "Aprovado" : "Reprovado";
                String motivo = taMotivo.getText().trim();

                if (status.equals("Reprovado") && motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Para laudos reprovados, é necessário informar o motivo!",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Laudo laudo = new Laudo();
                laudo.setStatus(status);
                laudo.setMotivo(motivo);
                laudo.setDataEmissao(new Date());
                laudo.setVistoriador(vistoriador);

                if (AgendamentoController.emitirLaudo(idAgendamento, laudo)) {
                    JOptionPane.showMessageDialog(this,
                            "Laudo emitido com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    carregarAgendamentos();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao emitir laudo: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
