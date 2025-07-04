package view;

import javax.swing.*;

public class AtendenteView extends JFrame {
    public AtendenteView(String user) {
        setTitle("Área do Atendente - " + user);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo, Atendente " + user, JLabel.CENTER);
        add(lblBemVindo);
    }
}
