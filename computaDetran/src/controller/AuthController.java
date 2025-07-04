package controller;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class AuthController {
    private List<Usuario> usuarios;

    public AuthController() {
        // Isso seria substituído por acesso a banco de dados
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("cliente1", "123"));
        usuarios.add(new Usuario("vistoriador1", "123"));
        usuarios.add(new Usuario("atendente1", "123"));
    }

    public Usuario autenticar(String login, String senha) {
        return usuarios.stream().filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha)).findFirst().orElse(null);
    }

}
