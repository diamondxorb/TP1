package view;

import controller.PessoaController;
import model.Atendente;
import util.HashUtil;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroAtendente extends JFrame {

    public TelaCadastroAtendente() {
        setTitle("Cadastro de Atendente");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtNascimento = new JTextField();
        JTextField txtEndereco = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtCelular = new JTextField();
        JTextField txtNumeroId = new JTextField();
        JPasswordField txtSenha = new JPasswordField();

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Nascimento (YYYY-MM-DD):"));
        panel.add(txtNascimento);
        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);
        panel.add(new JLabel("E-mail:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Celular:"));
        panel.add(txtCelular);
        panel.add(new JLabel("Número de Identificação:"));
        panel.add(txtNumeroId);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);

        JButton btnSalvar = new JButton("Salvar Atendente");
        btnSalvar.addActionListener(e -> {
            try {
                if (txtNome.getText().isBlank() || txtCpf.getText().length() != 11 || txtNumeroId.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Preencha nome, CPF (11 dígitos) e número de identificação.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int numeroId = Integer.parseInt(txtNumeroId.getText());
                String senhaHash = HashUtil.sha256(new String(txtSenha.getPassword()));

                Atendente a = new Atendente(
                        txtNome.getText(), txtCpf.getText(), txtNascimento.getText(),
                        txtEndereco.getText(), txtEmail.getText(), txtCelular.getText(),
                        numeroId, senhaHash
                );
                PessoaController.salvarAtendente(a);
                JOptionPane.showMessageDialog(this, "Atendente salvo com sucesso!");
                this.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número de identificação inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel(""));
        panel.add(btnSalvar);

        add(panel);
    }
}
