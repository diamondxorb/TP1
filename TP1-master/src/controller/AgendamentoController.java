package controller;

import model.Agendamento;
import model.Atendente;
import model.Vistoriador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AgendamentoController {

    public static void salvarAgendamento(Agendamento ag) throws SQLException {
        String sql = "INSERT INTO agendamento (data, horario, motivoAgendamento, id_atendente, id_vistoriador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ag.getData());
            stmt.setString(2, ag.getHorario());
            stmt.setString(3, ag.getMotivoAgendamento());
            stmt.setInt(4, ag.getAtendente().getId());
            stmt.setObject(5, ag.getVistoriador() != null ? ag.getVistoriador().getId() : null, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    public static List<Agendamento> listarAgendamentos() throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        String sql = """
            SELECT a.id, a.data, a.horario, a.motivoAgendamento,
                   p.id AS atendente_id, p.nome AS atendente_nome, p.cpf AS atendente_cpf,
                   p.data_nascimento AS atendente_dn, p.endereco AS atendente_end, p.email AS atendente_email,
                   p.celular AS atendente_cel, p.numeroIdentificacao, p.senha,
                   v.id AS vistoriador_id, v.nome AS vistoriador_nome
            FROM agendamento a
            JOIN pessoa p ON a.id_atendente = p.id
            LEFT JOIN pessoa v ON a.id_vistoriador = v.id
            WHERE p.tipo = 'Atendente'
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Atendente atendente = new Atendente(
                        rs.getInt("atendente_id"),
                        rs.getString("atendente_nome"),
                        rs.getString("atendente_cpf"),
                        rs.getString("atendente_dn"),
                        rs.getString("atendente_end"),
                        rs.getString("atendente_email"),
                        rs.getString("atendente_cel"),
                        rs.getInt("numeroIdentificacao"),
                        rs.getString("senha")
                );

                Vistoriador vistoriador = null;
                if (rs.getInt("vistoriador_id") != 0) {
                    vistoriador = new Vistoriador(
                            rs.getInt("vistoriador_id"),
                            rs.getString("vistoriador_nome"),
                            null, null, null, null, null, 0, null, null
                    );
                }

                Agendamento ag = new Agendamento(
                        rs.getInt("id"),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("motivoAgendamento"),
                        atendente,
                        vistoriador
                );

                lista.add(ag);
            }
        }
        return lista;
    }

    public static List<Agendamento> listarAgendamentosPorVistoriador(String nome) throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        String sql = """
        SELECT a.id, a.data, a.horario, a.motivoAgendamento,
               p.id AS atendente_id, p.nome AS atendente_nome, p.cpf AS atendente_cpf,
               p.data_nascimento AS atendente_dn, p.endereco AS atendente_end, 
               p.email AS atendente_email, p.celular AS atendente_cel, 
               p.numeroIdentificacao, p.senha,
               v.id AS vistoriador_id, v.nome AS vistoriador_nome, v.cpf AS vistoriador_cpf,
               v.data_nascimento AS vistoriador_dn, v.endereco AS vistoriador_end,
               v.email AS vistoriador_email, v.celular AS vistoriador_cel,
               v.registro_profissional, v.senha AS vistoriador_senha
        FROM agendamento a
        JOIN pessoa p ON a.id_atendente = p.id
        JOIN pessoa v ON a.id_vistoriador = v.id
        WHERE v.tipo = 'Vistoriador' AND v.nome LIKE ?
        ORDER BY a.data, a.horario
    """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Atendente atendente = new Atendente(
                            rs.getInt("atendente_id"),
                            rs.getString("atendente_nome"),
                            rs.getString("atendente_cpf"),
                            rs.getString("atendente_dn"),
                            rs.getString("atendente_end"),
                            rs.getString("atendente_email"),
                            rs.getString("atendente_cel"),
                            rs.getInt("numeroIdentificacao"),
                            rs.getString("senha")
                    );

                    Vistoriador vistoriador = new Vistoriador(
                            rs.getInt("vistoriador_id"),
                            rs.getString("vistoriador_nome"),
                            rs.getString("vistoriador_cpf"),
                            rs.getString("vistoriador_dn"),
                            rs.getString("vistoriador_end"),
                            rs.getString("vistoriador_email"),
                            rs.getString("vistoriador_cel"),
                            rs.getInt("numeroIdentificacao"),
                            rs.getString("vistoriador_senha"),
                            "Vistoriador"
                    );

                    Agendamento ag = new Agendamento(
                            rs.getInt("id"),
                            rs.getString("data"),
                            rs.getString("horario"),
                            rs.getString("motivoAgendamento"),
                            atendente,
                            vistoriador
                    );

                    lista.add(ag);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamentos:" + e.getMessage());
            e.printStackTrace();
            //Retorna lista vazia em caso de erro
            return Collections.emptyList();
        }
        return lista;
    }

    public static void updateAgendamento(Agendamento ag) throws SQLException {
        String sql = "UPDATE agendamento SET data=?, horario=?, motivoAgendamento=?, id_atendente=?, id_vistoriador=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ag.getData());
            stmt.setString(2, ag.getHorario());
            stmt.setString(3, ag.getMotivoAgendamento());
            stmt.setInt(4, ag.getAtendente().getId());
            stmt.setObject(5, ag.getVistoriador() != null ? ag.getVistoriador().getId() : null, Types.INTEGER);
            stmt.setInt(6, ag.getId());

            stmt.executeUpdate();
        }
    }

    public static void deleteAgendamento(int id) throws SQLException {
        String sql = "DELETE FROM agendamento WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
