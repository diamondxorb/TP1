package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controller.UsuarioController;

public class LoginView extends JFrame {
    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnCadastro;

    public LoginView() {
        setTitle("Login - computaDetran");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblTitulo = new JLabel("computaDetran", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitulo, gbc);

        // Campo Usuário
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("CPF:"), gbc);

        gbc.gridx = 1;
        txtCpf = new JTextField(15);
        panel.add(txtCpf, gbc);

        // Campo Senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        txtSenha = new JPasswordField(15);
        panel.add(txtSenha, gbc);

        // Botão Login
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        panel.add(btnLogin, gbc);

        // Botão Cadastro
        gbc.gridy = 4;
        btnCadastro = new JButton("Criar nova conta");
        btnCadastro.setPreferredSize(new Dimension(150, 25));
        btnCadastro.setForeground(new Color(0, 100, 200));
        btnCadastro.setContentAreaFilled(false);
        btnCadastro.setBorderPainted(false);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnCadastro, gbc);

        // Adiciona ações
        btnLogin.addActionListener(this::realizarLogin);
        btnCadastro.addActionListener(e -> abrirTelaCadastro());

        add(panel);
    }

    private void realizarLogin(ActionEvent e) {
        String cpf = txtCpf.getText();
        String senha = new String(txtSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Preencha todos os campos!","Erro de Login", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioController usuarioController = new UsuarioController();
        if(!usuarioController.usuarioExiste(cpf)) {
            JOptionPane.showMessageDialog(this, "Sua conta não existe!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        } else if(!usuarioController.senhaCorreta(cpf, senha)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }

        String perfil = usuarioController.retornaPerfil(cpf, senha);
        if(perfil!=null) {
            abrirTelaAposLogin(cpf, perfil);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao identificar perfil do usuário!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaCadastro() {
        this.dispose();
        new CadastroView().setVisible(true);
    }

    private void abrirTelaAposLogin(String cpf, String perfil) {
        this.dispose();
        switch (perfil.toUpperCase()) {
            case "CLIENTE":
                new ClienteView(cpf).setVisible(true);
                break;
            case "VISTORIADOR":
                new VistoriadorView(cpf).setVisible(true);
                break;
            case "ATENDENTE":
                new AtendenteView(cpf).setVisible(true);
                break;
        }
    }
}