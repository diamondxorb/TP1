package view;

import controller.PessoaController;
import model.Vistoriador;
import util.HashUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastroVistoriadorView extends JFrame {

    private JTextField txtNome, txtCpf, txtNascimento, txtEndereco, txtEmail, txtCelular, txtCadastro, txtAssinatura;
    private JPasswordField txtSenha;
    private JButton btnSalvar;

    public CadastroVistoriadorView() {
        setTitle("Cadastro de Vistoriador");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Nome:", "CPF:", "Data de Nascimento:", "Endereço:", "E-mail:", "Celular:", "Cadastro Detran:", "Assinatura:", "Senha:"};
        JTextField[] fields = new JTextField[labels.length];
        txtNome = new JTextField(); txtCpf = new JTextField(); txtNascimento = new JTextField();
        txtEndereco = new JTextField(); txtEmail = new JTextField(); txtCelular = new JTextField();
        txtCadastro = new JTextField(); txtAssinatura = new JTextField(); txtSenha = new JPasswordField();
        fields[0]=txtNome; fields[1]=txtCpf; fields[2]=txtNascimento; fields[3]=txtEndereco;
        fields[4]=txtEmail; fields[5]=txtCelular; fields[6]=txtCadastro; fields[7]=txtAssinatura;

        gbc.gridx = 0; gbc.gridy = 0;
        for (int i = 0; i < labels.length; i++) {
            mainPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            mainPanel.add(i < 8 ? fields[i] : txtSenha, gbc);
            gbc.gridx = 0; gbc.gridy++;
        }

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(255, 204, 0));
        gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            try {
                int cadastro = Integer.parseInt(txtCadastro.getText());
                String senhaHash = HashUtil.sha256(new String(txtSenha.getPassword()));
                Vistoriador v = new Vistoriador(
                        txtNome.getText(), txtCpf.getText(), txtNascimento.getText(),
                        txtEndereco.getText(), txtEmail.getText(), txtCelular.getText(),
                        cadastro, txtAssinatura.getText(), senhaHash);
                PessoaController.salvarVistoriador(v);
                JOptionPane.showMessageDialog(this, "Vistoriador salvo com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}