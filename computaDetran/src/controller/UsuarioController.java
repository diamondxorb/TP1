package controller;

import model.Pessoa;
import utils.DatabaseConnection;
import java.sql.*;
import model.Proprietario;
import model.Vistoriador;
import model.Atendente;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Pessoa> pessoas = new ArrayList<>();

    //Função para cadastrar usuários no sistema, utiliza sobrecarga conforme a quantidade de parâmetros
    //cadastrarPessoa para Proprietário
    public boolean cadastrarPessoa(String nome, String cpf, String dataNasc, String endereco, String email, String celular, String senha) {
        try {
            if(usuarioExiste(cpf)) {
                return false;
            }
            Proprietario proprietario = new Proprietario(nome, cpf, dataNasc, endereco, email, celular, senha);
            pessoas.add(proprietario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //cadastrarPessoa para Atendente
    public boolean cadastrarPessoa(String nome, String cpf, String dataNasc, String endereco, String email, String celular, String senha, int numeroIdentificacao) {
        try {
            if(usuarioExiste(cpf)) {
                return false;
            }
            Atendente atendente = new Atendente(nome, cpf, dataNasc, endereco, email, celular, senha, numeroIdentificacao);
            pessoas.add(atendente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //cadastrarPessoa para Vistoriador
    public boolean cadastrarPessoa(String nome, String cpf, String dataNasc, String endereco, String email, String celular, String senha, String cadastroDetran, String assinatura) {
        try {
            if(usuarioExiste(cpf)) {
                return false;
            }
            Vistoriador vistoriador = new Vistoriador(nome, cpf, dataNasc, endereco, email, celular, senha, cadastroDetran, assinatura);
            pessoas.add(vistoriador);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean usuarioExiste(String cpf) {
        for(int i=0; i<pessoas.size(); i++) {
            if(pessoas.get(i).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public boolean senhaCorreta(String cpf, String senha) {
        for(int i=0; i<pessoas.size(); i++) {
            if(pessoas.get(i).getCpf().equals(cpf)) {
                if(pessoas.get(i).getSenha().equals(senha)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    public boolean usuarioExiste(String cpf) {
        return pessoas.stream().anyMatch(p -> p.getCpf().equals(cpf));
    }

    public boolean senhaCorreta(String cpf, String senha) {
        return pessoas.stream().filter(p -> p.getCpf().equals(cpf)).findFirst().map(p -> p.getSenha().equals(senha)).orElse(false);
    }

    public boolean usuarioExiste(String cpf) {
        return usuarios.stream().anyMatch(u -> u.getLogin().equals(cpf));
    }

    public boolean senhaCorreta(String cpf, String senha) {
        return usuarios.stream().filter(u -> u.getLogin().equals(cpf)).findFirst().map(u -> u.getSenha().equals(senha)).orElse(false);
    }

    public String retornaPerfil(String cpf, String senha) {
        return usuarios.stream().filter(u -> u.getLogin().equals(cpf) && u.getSenha().equals(senha)).findFirst().map(Usuario::getPerfil).orElse("");
    }

     */
}