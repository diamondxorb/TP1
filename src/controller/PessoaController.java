package controller;

import model.Atendente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    public static void salvarAtendente(Atendente atendente) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, cpf, data_nascimento, endereco, email, celular, tipo, numeroIdentificacao, senha) VALUES (?, ?, ?, ?, ?, ?, 'Atendente', ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atendente.getNome());
            stmt.setString(2, atendente.getCpf());
            stmt.setString(3, atendente.getDataNascimento());
            stmt.setString(4, atendente.getEndereco());
            stmt.setString(5, atendente.getEmail());
            stmt.setString(6, atendente.getCelular());
            stmt.setInt(7, atendente.getNumeroIdentificacao());
            stmt.setString(8, atendente.getSenhaHash());
            stmt.executeUpdate();
        }
    }

    public static void atualizarAtendente(Atendente atendente) throws SQLException {
        String sql = "UPDATE pessoa SET nome=?, cpf=?, data_nascimento=?, endereco=?, email=?, celular=?, numeroIdentificacao=?, senha=? WHERE id=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atendente.getNome());
            stmt.setString(2, atendente.getCpf());
            stmt.setString(3, atendente.getDataNascimento());
            stmt.setString(4, atendente.getEndereco());
            stmt.setString(5, atendente.getEmail());
            stmt.setString(6, atendente.getCelular());
            stmt.setInt(7, atendente.getNumeroIdentificacao());
            stmt.setString(8, atendente.getSenhaHash());
            stmt.setInt(9, atendente.getId());
            stmt.executeUpdate();
        }
    }

    public static void excluirAtendente(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id=?";
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
                Atendente atendente = new Atendente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getInt("numeroIdentificacao"),
                        rs.getString("senha")
                );
                lista.add(atendente);
            }
        }
        return lista;
    }
}
