package view;

import javax.swing.*;
import java.awt.*;
import model.Agendamento;

public class DetalhesAgendamentoView extends JFrame {
    public DetalhesAgendamentoView(Agendamento agendamento) {
        setTitle("Detalhes do Agendamento n° " + agendamento.getId());
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(String.valueOf(agendamento.getId())));

        panel.add(new JLabel("Data/Hora:"));
        panel.add(new JLabel(agendamento.getDataHoraFormatada()));

        panel.add(new JLabel("Cliente:"));
        panel.add(new JLabel(agendamento.getCliente()));

        panel.add(new JLabel("Local:"));
        panel.add(new JLabel(agendamento.getLocal()));

        panel.add(new JLabel("Status:"));
        panel.add(new JLabel(agendamento.getStatus()));

        if (agendamento.getLaudo() != null) {
            panel.add(new JLabel("Laudo:"));
            panel.add(new JLabel(agendamento.getLaudo().getStatus()));

            panel.add(new JLabel("Motivo:"));
            panel.add(new JLabel(agendamento.getLaudo().getMotivo()));

            panel.add(new JLabel("Emissão do Laudo:"));
            panel.add(new JLabel(agendamento.getLaudo().getDataEmissaoFormatada()));
        }

        add(panel);
    }

}
