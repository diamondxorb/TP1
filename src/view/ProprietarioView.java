package view;

import controller.LaudoController;
import controller.SolicitacaoController;
import model.Proprietario;
import model.SolicitacaoAgendamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class ProprietarioView extends JFrame {
    private final SolicitacaoController controller;
    private final Proprietario proprietario;
    private DefaultTableModel tabelaModel;

    public ProprietarioView(SolicitacaoController controller) {
        this.controller = controller;
        this.proprietario = new Proprietario(
                "João da Silva",
                "12345678900",
                LocalDate.of(1990, 5, 15),
                "Rua das Flores, 123",
                "joao@email.com",
                "61999998888",
                "DF12345678",
                "senhaSegura"
        );

        setTitle("Área do Proprietário - " + proprietario.getNome());
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        atualizarLista();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cabeçalho
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Bem-vindo, Proprietário!", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Proprietário: " + proprietario.getNome() + " | CPF: " + proprietario.getCpf(), JLabel.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(lblTitle);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(lblSubtitle);

        // Tabela
        String[] columns = {"Local", "Status", "Motivo", "Veículo"};
        tabelaModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tabelaModel);
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Solicitações Recentes"));

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnSolicitar = new JButton("Nova Solicitação");
        btnSolicitar.setPreferredSize(new Dimension(180, 40));
        btnSolicitar.addActionListener(e -> {
            new SolicitacaoView(controller, proprietario).setVisible(true);
            atualizarLista();
        });

        JButton btnLaudos = new JButton("Ver Laudos");
        btnLaudos.setPreferredSize(new Dimension(180, 40));
        btnLaudos.addActionListener(e -> {
            LaudoController laudoController = new LaudoController();
            new LaudoView(laudoController, proprietario).setVisible(true);
            atualizarLista();
        });

        buttonPanel.add(btnSolicitar);
        buttonPanel.add(btnLaudos);

        // Layout final
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void atualizarLista() {
        tabelaModel.setRowCount(0);

        for (SolicitacaoAgendamento s : controller.listarSolicitacoes()) {
            String motivo = "Negado".equals(s.getStatus()) ? s.getMotivoNegacao() : "-";
            String placa = (s.getVeiculo() != null) ? s.getVeiculo().getPlaca() : "N/A";

            tabelaModel.addRow(new Object[]{
                    s.getLocal(),
                    s.getStatus(),
                    motivo,
                    placa
            });
        }
    }
}
