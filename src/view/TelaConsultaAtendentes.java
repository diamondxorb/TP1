package view;

import controller.PessoaController;
import model.Atendente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaConsultaAtendentes extends JFrame {

    public TelaConsultaAtendentes() {
        setTitle("Consulta de Atendentes");
        setSize(800, 400);
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
            List<Atendente> lista = PessoaController.listarAtendentes();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Nome", "CPF", "Nascimento", "Endereço", "E-mail", "Celular", "Nº Identificação"});

            for (Atendente a : lista) {
                model.addRow(new Object[]{
                        a.getId(),
                        a.getNome(),
                        a.getCpf(),
                        a.getDataNascimento(),
                        a.getEndereco(),
                        a.getEmail(),
                        a.getCelular(),
                        a.getNumeroIdentificacao()
                });
            }

            tabela.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
