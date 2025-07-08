package controller;

import model.Agendamento;
import model.Atendente;
import model.Vistoriador;
import model.Laudo;
import model.Veiculo;
import model.Proprietario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {

    // Método existente (mantido para compatibilidade)
    public static void salvarAgendamento(Agendamento ag) throws SQLException {
        String sql = "INSERT INTO agendamento (data, horario, motivoAgendamento, id_atendente, id_vistoriador, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ag.getData());
            stmt.setString(2, ag.getHorario());
            stmt.setString(3, ag.getMotivoAgendamento());
            stmt.setInt(4, ag.getAtendente().getId());
            stmt.setObject(5, ag.getVistoriador() != null ? ag.getVistoriador().getId() : null, Types.INTEGER);
            stmt.setString(6, ag.getStatus());
            stmt.executeUpdate();
        }
    }

    // Novo método para criar agendamento pelo proprietário
    public static boolean criarAgendamento(String data, String horario, String motivo,
                                           String placa, String modelo, Proprietario proprietario) {
        String sql = "INSERT INTO agendamento (data, horario, motivoAgendamento, status) VALUES (?, ?, ?, 'Pendente')";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, data);
            stmt.setString(2, horario);
            stmt.setString(3, motivo);
            stmt.executeUpdate();

            // Obter ID do agendamento criado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idAgendamento = rs.getInt(1);
                    // Associar veículo (cria se não existir)
                    associarVeiculo(conn, idAgendamento, placa, modelo, proprietario);
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void associarVeiculo(Connection conn, int idAgendamento, String placa,
                                        String modelo, Proprietario proprietario) throws SQLException {
        // Verificar se veículo existe
        String sqlCheck = "SELECT id FROM veiculo WHERE placa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlCheck)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            int idVeiculo;
            if (rs.next()) {
                idVeiculo = rs.getInt("id");
            } else {
                // Criar novo veículo
                String sqlInsert = "INSERT INTO veiculo (placa, modelo, id_proprietario) VALUES (?, ?, ?)";
                try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                    stmtInsert.setString(1, placa);
                    stmtInsert.setString(2, modelo);
                    stmtInsert.setInt(3, proprietario.getId());
                    stmtInsert.executeUpdate();

                    try (ResultSet rsInsert = stmtInsert.getGeneratedKeys()) {
                        if (rsInsert.next()) {
                            idVeiculo = rsInsert.getInt(1);
                        } else {
                            throw new SQLException("Falha ao obter ID do veículo");
                        }
                    }
                }
            }

            // Associar veículo ao agendamento
            String sqlUpdate = "UPDATE agendamento SET id_veiculo = ? WHERE id = ?";
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setInt(1, idVeiculo);
                stmtUpdate.setInt(2, idAgendamento);
                stmtUpdate.executeUpdate();
            }
        }
    }

    // Método para atendente aprovar agendamento
    public static boolean aprovarAgendamento(int idAgendamento, int idVistoriador) throws SQLException {
        String sql = "UPDATE agendamento SET status = 'Aprovado', id_vistoriador = ? WHERE id = ? AND status = 'Pendente'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVistoriador);
            stmt.setInt(2, idAgendamento);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método para atendente negar agendamento
    public static boolean negarAgendamento(int idAgendamento, String motivo) throws SQLException {
        String sql = "UPDATE agendamento SET status = 'Negado', motivo_negacao = ? WHERE id = ? AND status = 'Pendente'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motivo);
            stmt.setInt(2, idAgendamento);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método para vistoriador emitir laudo
    public static boolean emitirLaudo(int idAgendamento, Laudo laudo) throws SQLException {
        Connection conn = Conexao.getConnection();
        try {
            conn.setAutoCommit(false);

            // 1. Inserir o laudo
            String sqlLaudo = "INSERT INTO laudo (status, motivo, dataEmissao, id_vistoriador, id_agendamento) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlLaudo, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, laudo.getStatus());
                stmt.setString(2, laudo.getMotivo());
                stmt.setTimestamp(3, new java.sql.Timestamp(laudo.getDataEmissao().getTime()));
                stmt.setInt(4, laudo.getVistoriador().getId());
                stmt.setInt(5, idAgendamento);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        laudo.setId(rs.getInt(1));
                    }
                }
            }

            // 2. Atualizar status do agendamento
            String sqlAgendamento = "UPDATE agendamento SET status = 'Concluído' WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlAgendamento)) {
                stmt.setInt(1, idAgendamento);
                stmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // Método para listar agendamentos por vistoriador (usado na VistoriadorAgendamentosView)
    public static List<Agendamento> listarAgendamentosPorVistoriador(int idVistoriador) throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        String sql = "SELECT a.id, a.data, a.horario, a.motivoAgendamento, " +
                "v.placa, v.modelo, p.nome as proprietario_nome " +
                "FROM agendamento a " +
                "JOIN veiculo v ON a.id_veiculo = v.id " +
                "JOIN pessoa p ON v.id_proprietario = p.id " +
                "WHERE a.id_vistoriador = ? AND a.status = 'Aprovado'";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVistoriador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento ag = new Agendamento();
                    ag.setId(rs.getInt("id"));
                    ag.setData(rs.getString("data"));
                    ag.setHorario(rs.getString("horario"));
                    ag.setMotivoAgendamento(rs.getString("motivoAgendamento"));

                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setModelo(rs.getString("modelo"));

                    Proprietario proprietario = new Proprietario();
                    proprietario.setNome(rs.getString("proprietario_nome"));
                    veiculo.setProprietario(proprietario);

                    ag.setVeiculo(veiculo);
                    lista.add(ag);
                }
            }
        }
        return lista;
    }

    // Método auxiliar para buscar agendamento por ID
    public static Agendamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT a.*, v.placa, v.modelo, p.nome as proprietario_nome " +
                "FROM agendamento a " +
                "JOIN veiculo v ON a.id_veiculo = v.id " +
                "JOIN pessoa p ON v.id_proprietario = p.id " +
                "WHERE a.id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agendamento ag = new Agendamento();
                    ag.setId(rs.getInt("id"));
                    ag.setData(rs.getString("data"));
                    ag.setHorario(rs.getString("horario"));
                    ag.setMotivoAgendamento(rs.getString("motivoAgendamento"));
                    ag.setStatus(rs.getString("status"));

                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setModelo(rs.getString("modelo"));

                    Proprietario proprietario = new Proprietario();
                    proprietario.setNome(rs.getString("proprietario_nome"));
                    veiculo.setProprietario(proprietario);

                    ag.setVeiculo(veiculo);
                    return ag;
                }
            }
        }
        return null;
    }

    // Método para excluir um agendamento
    public static boolean deleteAgendamento(int idAgendamento) {
        String sql = "DELETE FROM agendamento WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAgendamento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os agendamentos
    public static List<Agendamento> listarAgendamentos() {
        List<Agendamento> lista = new ArrayList<>();
        try {
            String sql = "SELECT a.id, a.data, a.horario, a.status, " +
                    "v.placa, v.modelo, p.nome as proprietario_nome, p.cpf as proprietario_cpf " +
                    "FROM agendamento a " +
                    "JOIN veiculo v ON a.id_veiculo = v.id " +
                    "JOIN pessoa p ON v.id_proprietario = p.id";

            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Agendamento ag = new Agendamento();
                    ag.setId(rs.getInt("id"));
                    ag.setData(rs.getString("data"));
                    ag.setHorario(rs.getString("horario"));
                    ag.setStatus(rs.getString("status"));

                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setModelo(rs.getString("modelo"));

                    Proprietario proprietario = new Proprietario();
                    proprietario.setNome(rs.getString("proprietario_nome"));
                    proprietario.setCpf(rs.getString("proprietario_cpf"));
                    veiculo.setProprietario(proprietario);

                    ag.setVeiculo(veiculo);
                    lista.add(ag);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
