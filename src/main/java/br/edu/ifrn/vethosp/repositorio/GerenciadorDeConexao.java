package br.edu.ifrn.vethosp.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class GerenciadorDeConexao {

    private static final String URL_PADRAO = "jdbc:mysql://localhost:3306/vethosp_db?useTimezone=true&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_PADRAO, "root", "");
    }

    public static String getUrl() {
        return URL_PADRAO;
    }
}
