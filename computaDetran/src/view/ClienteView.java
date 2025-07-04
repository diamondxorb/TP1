package view;

import javax.swing.*;

public class ClienteView extends JFrame {
    public ClienteView(String user) {
        setTitle("Área do Cliente - " + user);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo, Cliente " + user, JLabel.CENTER);
        add(lblBemVindo);

    }
}
