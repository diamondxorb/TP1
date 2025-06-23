package view;

import controller.AgendamentoController;
import controller.PessoaController;
import model.Agendamento;
import model.Atendente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaAgendamento extends JFrame {

    private List<Atendente> listaAtendentes;

    public TelaAgendamento() {
        setTitle("Novo Agendamento de Vistoria");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField txtData = new JTextField();
        JTextField txtHorario = new JTextField();
        JTextField txtMotivo = new JTextField();
        JComboBox<String> comboAtendentes = new JComboBox<>();

        carregarAtendentes(comboAtendentes);

        panel.add(new JLabel("Data (YYYY-MM-DD):"));
        panel.add(txtData);
        panel.add(new JLabel("Horário (HH:MM):"));
        panel.add(txtHorario);
        panel.add(new JLabel("Motivo:"));
        panel.add(txtMotivo);
        panel.add(new JLabel("Atendente:"));
        panel.add(comboAtendentes);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.addActionListener(e -> {
            try {
                if (txtData.getText().isBlank() || txtHorario.getText().isBlank() || txtMotivo.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Atendente atendente = listaAtendentes.get(comboAtendentes.getSelectedIndex());
                Agendamento ag = new Agendamento(
                        txtData.getText(),
                        txtHorario.getText(),
                        txtMotivo.getText(),
                        atendente
                );
                AgendamentoController.salvarAgendamento(ag);
                JOptionPane.showMessageDialog(this, "Agendamento salvo com sucesso!");
                this.dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel(""));
        panel.add(btnAgendar);

        add(panel);
    }

    private void carregarAtendentes(JComboBox<String> combo) {
        try {
            listaAtendentes = PessoaController.listarAtendentes();
            combo.removeAllItems();
            for (Atendente a : listaAtendentes) {
                combo.addItem(a.getNome() + " (" + a.getNumeroIdentificacao() + ")");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar atendentes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
