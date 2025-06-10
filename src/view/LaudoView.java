package view;

import controller.LaudoController;
import model.Laudo;
import model.Proprietario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LaudoView extends JFrame {
    public LaudoView(LaudoController controller, Proprietario proprietario) {
        setTitle("Meus Laudos - " + proprietario.getNome());
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(controller, proprietario);
    }

    private void initUI(LaudoController controller, Proprietario proprietario) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Laudos do Veículo", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblSubtitle = new JLabel("Proprietário: " + proprietario.getNome() + " | CPF: " + proprietario.getCpf(), JLabel.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblSubtitle, BorderLayout.SOUTH);

        // Tabela de laudos
        String[] columns = {"Data", "Placa", "Modelo", "Status", "Vistoriador"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(25);

        // Preenche a tabela
        controller.listarPorProprietario(proprietario.getCpf()).forEach(laudo -> {
            Object[] row = {
                    laudo.getDataEmissaoFormatada(),
                    laudo.getVeiculo().getPlaca(),
                    laudo.getVeiculo().getModelo(),
                    laudo.getStatus(),
                    laudo.getVistoriador().getNome()
            };
            model.addRow(row);
        });

        JScrollPane scrollPane = new JScrollPane(table);

        // Painel de detalhes
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Detalhes do Laudo"));
        JTextArea taDetails = new JTextArea(8, 60);
        taDetails.setEditable(false);
        detailPanel.add(new JScrollPane(taDetails), BorderLayout.CENTER);

        // Listener para seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Laudo laudo = controller.listarPorProprietario(proprietario.getCpf()).get(row);
                taDetails.setText(formatLaudoDetails(laudo));
            }
        });

        // Layout principal
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(detailPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private String formatLaudoDetails(Laudo laudo) {
        return String.format(
                "DATA: %s\n" +
                        "VEÍCULO: %s (%s)\n" +
                        "STATUS: %s\n" +
                        "VISTORIADOR: %s\n" +
                        "MOTIVO: %s\n" +
                        "DOCUMENTAÇÃO: %s",
                laudo.getDataEmissaoFormatada(),
                laudo.getVeiculo().getModelo(),
                laudo.getVeiculo().getPlaca(),
                laudo.getStatus(),
                laudo.getVistoriador().getNome(),
                laudo.getMotivo(),
                laudo.getVeiculo().isDocumentoPago() ? "Regular" : "Pendente"
        );
    }
}