package view;

import controller.LaudoController;
import controller.SolicitacaoController;
import model.*;
import repository.LaudoRepository;
import repository.LaudoRepositoryMemoria;
import util.MotivoComboBoxRenderer;
import util.MotivosVistoria;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ProprietarioView extends JFrame {
    private SolicitacaoController controller;
    private Proprietario proprietario;

    public ProprietarioView(SolicitacaoController controller, Proprietario proprietario) {
        this.controller = controller;
        this.proprietario = proprietario;

        setTitle("Sistema de Vistoria - Área do Proprietário");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Painel principal com borda
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cabeçalho com boas-vindas
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblWelcome = new JLabel("Bem-vindo, " + proprietario.getNome() + "!", JLabel.LEFT);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel lblInfo = new JLabel("CPF: " + proprietario.getCpf() + " | Cadastro: " + proprietario.getCadastroDetran());
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        headerPanel.add(lblWelcome, BorderLayout.NORTH);
        headerPanel.add(lblInfo, BorderLayout.SOUTH);

        // Formulário de solicitação
        JPanel formPanel = createFormPanel();

        // Lista de solicitações
        JPanel requestsPanel = createRequestsPanel();

        // Layout principal
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(requestsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Nova Solicitação de Vistoria"));

        // Campos do formulário
        JTextField txtPlaca = new JTextField();
        JTextField txtModelo = new JTextField();

        // Combobox de motivos com tooltips
        JComboBox<String> cmbMotivo = new JComboBox<>(MotivosVistoria.getMotivos());
        cmbMotivo.setRenderer(new MotivoComboBoxRenderer());

        // Adiciona tooltips para cada item
        for (int i = 0; i < cmbMotivo.getItemCount(); i++) {
            String motivo = cmbMotivo.getItemAt(i);
            cmbMotivo.setToolTipText(MotivosVistoria.getDescricao(motivo));
        }
        JCheckBox chkDocumento = new JCheckBox(" Documentação em dia");
        JTextField txtLocal = new JTextField();
        JTextArea txtObservacoes = new JTextArea(3, 20);
        JScrollPane scrollObservacoes = new JScrollPane(txtObservacoes);

        // Adiciona componentes
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

        // Botão de enviar
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
        });

        panel.add(new JLabel(""));
        panel.add(btnEnviar);

        return panel;
    }

    private JPanel createRequestsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Minhas Solicitações"));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> requestList = new JList<>(listModel);
        JScrollPane scroll = new JScrollPane(requestList);

        // Botões de ação
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefresh = new JButton("Atualizar");
        JButton btnViewLaudos = new JButton("Ver Laudos");

        btnRefresh.addActionListener(e -> updateRequestsList(listModel));
        btnViewLaudos.addActionListener(e -> showLaudosView());

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnViewLaudos);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Carrega dados iniciais
        updateRequestsList(listModel);

        return panel;
    }

    private void updateRequestsList(DefaultListModel<String> model) {
        model.clear();
        controller.listarSolicitacoes().forEach(s -> {
            String info = String.format("[%s] %s - %s | Motivo: %s | Status: %s",
                    s.getVeiculo().getPlaca(),
                    s.getVeiculo().getModelo(),
                    s.getLocal(),
                    s.getMotivo().length() > 20 ?
                            s.getMotivo().substring(0, 17) + "..." :
                            s.getMotivo(),
                    s.getStatus()
            );
            model.addElement(info);
        });
    }

    private void showLaudosView() {
        LaudoRepository repository = new LaudoRepositoryMemoria();
        LaudoController controller = new LaudoController(repository);
        new LaudoView(controller, proprietario).setVisible(true);
    }
}