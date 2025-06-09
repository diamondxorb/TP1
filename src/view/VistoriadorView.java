package view;

import java.time.LocalDate;
import model.Vistoriador;
import javax.swing.*;
import java.awt.*;

public class VistoriadorView extends JFrame {
    public VistoriadorView(String user) {
        setTitle("Área do Vistoriador - " + user);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Vistoriador vistoriador = new Vistoriador(user, "09276627103", LocalDate.of(2006, 6, 9),
                "Vicente Pires", "juliaamorimp@gmail.com", "996601744", "1234", "abc");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel lblBemVindo = new JLabel("Bem-vinda, Vistoriadora " + user, JLabel.CENTER);
        lblBemVindo.setFont(new Font("Comic Sans", Font.BOLD, 18));
        mainPanel.add(lblBemVindo, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton btnAgendamentos = new JButton("Meus Agendamentos");
        btnAgendamentos.addActionListener(e -> {
            new VistoriadorAgendamentosView(vistoriador).setVisible(true);
        });

        buttonPanel.add(btnAgendamentos);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
}
