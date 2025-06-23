package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {


    private static final String URL = "jdbc:sqlite:computaDetran.db";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }




    public static void inicializarBanco() {
        String sql = """
            CREATE TABLE IF NOT EXISTS pessoa (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                cpf TEXT NOT NULL,
                data_nascimento TEXT,
                endereco TEXT,
                email TEXT,
                celular TEXT,
                tipo TEXT NOT NULL
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela 'pessoa' criada ou já existente.");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela 'pessoa': " + e.getMessage());
        }
    }

    public static void inicializarTabelaAgendamento() {
        String sql = """
        CREATE TABLE IF NOT EXISTS agendamento (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            data TEXT,
            horario TEXT,
            id_atendente INTEGER,
            FOREIGN KEY(id_atendente) REFERENCES pessoa(id)
        );
    """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'agendamento' criada/verificada.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela 'agendamento': " + e.getMessage());
        }
    }
    public static void atualizarBanco() {
        // Adiciona numeroIdentificacao, se ainda não existir
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE pessoa ADD COLUMN numeroIdentificacao INTEGER");
        } catch (SQLException ignored) {}

        // Adiciona senha, se ainda não existir
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE pessoa ADD COLUMN senha TEXT");
        } catch (SQLException ignored) {}

        // Adiciona motivoAgendamento, se ainda não existir
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE agendamento ADD COLUMN motivoAgendamento TEXT");
        } catch (SQLException ignored) {}
    }
}
