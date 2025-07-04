package controller;

import model.Atendente;
import model.Vistoriador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    public static void salvarAtendente(Atendente a) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, cpf, data_nascimento, endereco, email, celular, tipo, numeroIdentificacao, senha) VALUES (?, ?, ?, ?, ?, ?, 'Atendente', ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getCpf());
            stmt.setString(3, a.getDataNascimento());
            stmt.setString(4, a.getEndereco());
            stmt.setString(5, a.getEmail());
            stmt.setString(6, a.getCelular());
            stmt.setInt(7, a.getNumeroIdentificacao());
            stmt.setString(8, a.getSenhaHash());
            stmt.executeUpdate();
        }
    }

    public static void atualizarAtendente(Atendente a) throws SQLException {
        String sql = "UPDATE pessoa SET nome=?, cpf=?, data_nascimento=?, endereco=?, email=?, celular=?, numeroIdentificacao=?, senha=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getCpf());
            stmt.setString(3, a.getDataNascimento());
            stmt.setString(4, a.getEndereco());
            stmt.setString(5, a.getEmail());
            stmt.setString(6, a.getCelular());
            stmt.setInt(7, a.getNumeroIdentificacao());
            stmt.setString(8, a.getSenhaHash());
            stmt.setInt(9, a.getId());
            stmt.executeUpdate();
        }
    }

    public static void excluirAtendente(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ? AND tipo = 'Atendente'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Atendente> listarAtendentes() throws SQLException {
        List<Atendente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa WHERE tipo = 'Atendente'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Atendente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getInt("numeroIdentificacao"),
                        rs.getString("senha")
                ));
            }
        }
        return lista;
    }

    public static List<Atendente> buscarAtendentes(String campo, String valor) throws SQLException {
        List<Atendente> lista = new ArrayList<>();
        if (!campo.equalsIgnoreCase("nome") && !campo.equalsIgnoreCase("cpf") && !campo.equalsIgnoreCase("numeroIdentificacao")) {
            throw new IllegalArgumentException("Campo de pesquisa inválido!");
        }
        String sql = "SELECT * FROM pessoa WHERE tipo = 'Atendente' AND " + campo + " LIKE ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Atendente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getInt("numeroIdentificacao"),
                        rs.getString("senha")
                ));
            }
        }
        return lista;
    }

    public static void salvarVistoriador(Vistoriador v) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, cpf, data_nascimento, endereco, email, celular, tipo) VALUES (?, ?, ?, ?, ?, ?, 'Vistoriador')";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getNome());
            stmt.setString(2, v.getCpf());
            stmt.setString(3, v.getDataNascimento());
            stmt.setString(4, v.getEndereco());
            stmt.setString(5, v.getEmail());
            stmt.setString(6, v.getCelular());
            stmt.executeUpdate();
        }
    }

    public static List<Vistoriador> listarVistoriadores() throws SQLException {
        List<Vistoriador> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa WHERE tipo = 'Vistoriador'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Vistoriador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("celular")
                ));
            }
        }
        return lista;
    }
}
