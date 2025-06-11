package view;

import controller.SolicitacaoController;
import model.Proprietario;
import model.SolicitacaoAgendamento;
import model.Veiculo;

import javax.swing.*;
import java.awt.*;

public class SolicitacaoView extends JFrame {
    private SolicitacaoController controller;
    private Proprietario proprietario;

    public SolicitacaoView(SolicitacaoController controller, Proprietario proprietario) {
        this.controller = controller;
        this.proprietario = proprietario;

        setTitle("Nova Solicitação de Vistoria");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField txtPlaca = new JTextField();
        JTextField txtModelo = new JTextField();

        JComboBox<String> cmbMotivo = new JComboBox<>();
        cmbMotivo.addItem("Transferência de propriedade");
        cmbMotivo.addItem("Mudança de município");
        cmbMotivo.addItem("Alteração de características");
        cmbMotivo.addItem("Segunda via do CRV");
        cmbMotivo.addItem("Baixa de veículo");
        cmbMotivo.addItem("Outros");


        JCheckBox chkDocumento = new JCheckBox(" Documentação em dia");
        JTextField txtLocal = new JTextField();
        JTextArea txtObservacoes = new JTextArea(3, 20);
        JScrollPane scrollObservacoes = new JScrollPane(txtObservacoes);

        panel.add(new JLabel("Placa do Veículo:"));
        panel.add(txtPlaca);
        panel.add(new JLabel("Modelo do Veículo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Motivo da Vistoria:"));
        panel.add(cmbMotivo);
        panel.add(new JLabel("Documentação:"));
        panel.add(chkDocumento);
        panel.add(new JLabel("Local Preferencial:"));
        panel.add(txtLocal);
        panel.add(new JLabel("Observações:"));
        panel.add(scrollObservacoes);

        JButton btnEnviar = new JButton("Solicitar Vistoria");
        btnEnviar.addActionListener(e -> {
            Veiculo veiculo = new Veiculo(
                    txtPlaca.getText(),
                    txtModelo.getText(),
                    proprietario,
                    chkDocumento.isSelected()
            );

            String motivo = cmbMotivo.getSelectedItem().toString();
            String observacoes = txtObservacoes.getText().trim();

            if (!observacoes.isEmpty()) {
                motivo += " - " + observacoes;
            }

            SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento(
                    txtLocal.getText(),
                    veiculo,
                    motivo
            );

            controller.solicitarAgendamento(solicitacao);
            JOptionPane.showMessageDialog(this, "Solicitação enviada com sucesso!");
            this.dispose(); // Fecha essa janela após enviar
        });

        panel.add(new JLabel(""));
        panel.add(btnEnviar);

        add(panel);
    }
}
