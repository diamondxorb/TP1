package controller;

import model.Laudo;
import repository.LaudoRepository;
import java.util.List;

public class LaudoController {
    private LaudoRepository repository;

    public LaudoController(LaudoRepository repository) {
        this.repository = repository;
    }

    public void emitirLaudo(Laudo laudo) {
        repository.salvar(laudo);
    }

    public List<Laudo> listarTodos() {
        return repository.listarTodos();
    }

    public List<Laudo> listarPorProprietario(String cpf) {
        return repository.listarPorProprietario(cpf);
    }
}