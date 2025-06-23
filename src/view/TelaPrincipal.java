package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema DETRAN - Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCadastrarAtendente = new JButton("Cadastrar Atendente");
        JButton btnAgendamento = new JButton("Novo Agendamento");
        JButton btnConsultaAtendentes = new JButton("Consultar Atendentes");
        JButton btnConsultaAgendamentos = new JButton("Consultar Agendamentos");

        btnCadastrarAtendente.addActionListener(e -> new TelaCadastroAtendente());
        btnAgendamento.addActionListener(e -> new TelaAgendamento());
        btnConsultaAtendentes.addActionListener(e -> new TelaConsultaAtendentes());
        btnConsultaAgendamentos.addActionListener(e -> new TelaConsultaAgendamentos());

        panel.add(lblTitulo);
        panel.add(btnCadastrarAtendente);
        panel.add(btnAgendamento);
        panel.add(btnConsultaAtendentes);
        panel.add(btnConsultaAgendamentos);

        add(panel);
    }
}
