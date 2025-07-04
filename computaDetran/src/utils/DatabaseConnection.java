package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:computaDetran.sqlite";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado", e);
        }
    }

    public static void criarTabelas() {
        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cpf TEXT NOT NULL UNIQUE,
                nome TEXT NOT NULL,
                data_nascimento TEXT NOT NULL,
                endereco TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                celular TEXT NOT NULL,
                senha TEXT NOT NULL,
                perfil TEXT NOT NULL,
                registro_profissional TEXT,
                matricula TEXT
            );
            """;

        String sqlAgendamentos = """
            CREATE TABLE IF NOT EXISTS agendamentos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                data_hora TEXT NOT NULL,
                cliente_cpf TEXT NOT NULL,
                vistoriador_cpf TEXT NOT NULL,
                local TEXT NOT NULL,
                status TEXT NOT NULL,
                FOREIGN KEY (cliente_cpf) REFERENCES usuarios(cpf),
                FOREIGN KEY (vistoriador_cpf) REFERENCES usuarios(cpf)
            );
            """;

        String sqlLaudos = """
            CREATE TABLE IF NOT EXISTS laudos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                agendamento_id INTEGER NOT NULL,
                status TEXT NOT NULL,
                motivo TEXT,
                data_emissao TEXT NOT NULL,
                vistoriador_cpf TEXT NOT NULL,
                FOREIGN KEY (agendamento_id) REFERENCES agendamentos(id),
                FOREIGN KEY (vistoriador_cpf) REFERENCES usuarios(cpf)
            );
            """;

        try (Connection conn = getConnection();
             var stmt = conn.createStatement()) {

            stmt.execute(sqlUsuarios);
            stmt.execute(sqlAgendamentos);
            stmt.execute(sqlLaudos);

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}