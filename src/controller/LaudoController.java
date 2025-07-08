package controller;

import model.Laudo;
import model.Proprietario;
import java.util.ArrayList;
import java.util.List;

public class LaudoController {
    private List<Laudo> laudos;

    public LaudoController() {
        this.laudos = new ArrayList<>();
    }

    // Mantido igual ao anterior
    public List<Laudo> listarPorProprietario(String cpfProprietario) {
        List<Laudo> result = new ArrayList<>();
        for (Laudo laudo : laudos) {
            if (laudo.getVeiculo().getProprietario().getCpf().equals(cpfProprietario)) {
                result.add(laudo);
            }
        }
        return result;
    }

    // Novo método para integração com agendamentos
    public boolean emitirLaudo(Laudo laudo) {
        try {
            laudos.add(laudo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}