package repository;

import model.Laudo;
import java.util.List;

public interface LaudoRepository {
    void salvar(Laudo laudo);
    List<Laudo> listarTodos();
    List<Laudo> listarPorProprietario(String cpfProprietario);
}
