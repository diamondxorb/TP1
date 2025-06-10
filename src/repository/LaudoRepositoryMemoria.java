package repository;

import model.Laudo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LaudoRepositoryMemoria implements LaudoRepository {
    private List<Laudo> laudos = new ArrayList<>();

    public void salvar(Laudo laudo) {
        laudos.add(laudo);
        System.out.println("Laudo salvo: " + laudo);
    }

    public List<Laudo> listarTodos() {
        return new ArrayList<>(laudos);
    }

    public List<Laudo> listarPorProprietario(String cpfProprietario) {
        return laudos.stream()
                .filter(l -> l.getProprietario().getCpf().equals(cpfProprietario))
                .collect(Collectors.toList());
    }
}