package view;

import controller.SolicitacaoController;
import model.SolicitacaoAgendamento;

import javax.swing.*;
import java.awt.*;

public class ProprietarioView extends JFrame {
    private SolicitacaoController controller;
    private DefaultListModel<String> listaModel;

    public ProprietarioView(SolicitacaoController controller) {
        super("Área do Proprietário");
        this.controller = controller;

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());

        // Lista de solicitações recentes
        listaModel = new DefaultListModel<>();
        JList<String> listaSolicitacoes = new JList<>(listaModel);
        JScrollPane scroll = new JScrollPane(listaSolicitacoes);
        painel.add(scroll, BorderLayout.CENTER);

        // Painel de botões
        JPanel botoes = new JPanel(new FlowLayout());
        JButton btnSolicitar = new JButton("Solicitar Novo Agendamento");
        JButton btnLaudos = new JButton("Verificar Laudos");

        btnSolicitar.addActionListener(e -> {
            String local = JOptionPane.showInputDialog(this, "Informe o local desejado para a vistoria:");
            if (local != null && !local.isBlank()) {
                SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento(local);
                controller.solicitarAgendamento(solicitacao);
                atualizarLista();
            }
        });

        btnLaudos.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidade de laudos ainda não implementada.");
        });

        botoes.add(btnSolicitar);
        botoes.add(btnLaudos);
        painel.add(botoes, BorderLayout.SOUTH);

        add(painel);
        atualizarLista();
    }

    private void atualizarLista() {
        listaModel.clear();
        for (SolicitacaoAgendamento s : controller.listarSolicitacoes()) {
            listaModel.addElement(s.toString());
        }
    }
}
