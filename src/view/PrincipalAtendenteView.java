package view;

import javax.swing.*;
import java.awt.*;

public class PrincipalAtendenteView extends JFrame {

    public PrincipalAtendenteView() {
        setTitle("Sistema DETRAN - Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCadastrarAtendente = new JButton("Cadastrar Atendente");
        JButton btnAgendamento = new JButton("Novo Agendamento");
        JButton btnConsultaAtendentes = new JButton("Consultar Atendentes");
        JButton btnConsultaAgendamentos = new JButton("Consultar Agendamentos");
        JButton btnCadastrarVistoriador = new JButton("Cadastrar Vistoriador");
        JButton btnSair = new JButton("Sair");

        btnCadastrarAtendente.addActionListener(e -> new CadastroAtendenteView());
        btnAgendamento.addActionListener(e -> new NovoAgendamentoView());
        btnConsultaAtendentes.addActionListener(e -> new ConsultaAtendentesView());
        btnConsultaAgendamentos.addActionListener(e -> new ConsultaAgendamentosView());
        btnCadastrarVistoriador.addActionListener(e -> new CadastroVistoriadorView());
        btnSair.addActionListener(e -> dispose());

        panel.add(lblTitulo);
        panel.add(btnCadastrarAtendente);
        panel.add(btnAgendamento);
        panel.add(btnConsultaAtendentes);
        panel.add(btnConsultaAgendamentos);
        panel.add(btnCadastrarVistoriador);
        panel.add(btnSair);

        add(panel);
    }
}