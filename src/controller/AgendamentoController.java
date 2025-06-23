package controller;

import model.Agendamento;
import model.Atendente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {

    public static void salvarAgendamento(Agendamento ag) throws SQLException {
        String sql = "INSERT INTO agendamento (data, horario, motivoAgendamento, id_atendente) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ag.getData());
            stmt.setString(2, ag.getHorario());
            stmt.setString(3, ag.getMotivoAgendamento());
            stmt.setInt(4, ag.getAtendente().getId());
            stmt.executeUpdate();
        }
    }

    public static List<Agendamento> listarAgendamentos() throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        String sql = """
            SELECT a.id, a.data, a.horario, a.motivoAgendamento,
                   p.id as atendente_id, p.nome, p.cpf, p.data_nascimento, p.endereco, p.email, p.celular, p.numeroIdentificacao, p.senha
            FROM agendamento a
            JOIN pessoa p ON a.id_atendente = p.id
            WHERE p.tipo = 'Atendente'
            """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Atendente atendente = new Atendente(
                        rs.getInt("atendente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getInt("numeroIdentificacao"),
                        rs.getString("senha")
                );
                Agendamento ag = new Agendamento(
                        rs.getInt("id"),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("motivoAgendamento"),
                        atendente
                );
                lista.add(ag);
            }
        }
        return lista;
    }
}
