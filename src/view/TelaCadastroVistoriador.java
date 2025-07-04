package view;

import controller.PessoaController;
import model.Vistoriador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaCadastroVistoriador extends JFrame {

    public TelaCadastroVistoriador() {
        setTitle("Cadastro de Vistoriador");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtNascimento = new JTextField();
        JTextField txtEndereco = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtCelular = new JTextField();

        panel.add(new JLabel("Nome:")); panel.add(txtNome);
        panel.add(new JLabel("CPF:")); panel.add(txtCpf);
        panel.add(new JLabel("Nascimento:")); panel.add(txtNascimento);
        panel.add(new JLabel("Endereço:")); panel.add(txtEndereco);
        panel.add(new JLabel("Email:")); panel.add(txtEmail);
        panel.add(new JLabel("Celular:")); panel.add(txtCelular);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(255, 204, 0));
        btnSalvar.addActionListener(e -> {
            try {
                Vistoriador v = new Vistoriador(
                        txtNome.getText(), txtCpf.getText(), txtNascimento.getText(),
                        txtEndereco.getText(), txtEmail.getText(), txtCelular.getText()
                );
                PessoaController.salvarVistoriador(v);
                JOptionPane.showMessageDialog(this, "Vistoriador salvo com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("")); panel.add(btnSalvar);

        add(panel);
        setVisible(true);
    }
}
