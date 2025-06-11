package controller;

import model.Laudo;
import model.Proprietario;
import model.Veiculo;
import model.Vistoriador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaudoController {
    public List<Laudo> listarPorProprietario(String cpf) {

        Proprietario p = new Proprietario("João da Silva", cpf, null, "", "", "", "", "");
        Vistoriador v = new Vistoriador("Carlos Técnico", "99999999999", null, "", "", "", "mat123", "senha123");
        Veiculo veiculo = new Veiculo("ABC-1234", "Fiat Uno", p, true);

        List<Laudo> laudos = new ArrayList<>();
        laudos.add(new Laudo("Aprovado", "Revisão periódica", new Date(), v, p, veiculo));
        laudos.add(new Laudo("Reprovado", "Documento vencido", new Date(), v, p, veiculo));

        return laudos;
    }
}
