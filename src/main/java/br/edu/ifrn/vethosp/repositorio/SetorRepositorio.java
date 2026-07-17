package br.edu.ifrn.vethosp.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.edu.ifrn.vethosp.modelo.Setor;

public class SetorRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR SETOR
    public void inserir(Setor setor) {
        String sql = "INSERT INTO setor (nome, capacidade_max, boxes_ocupados) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, setor.getNome());
            stmt.setInt(2, setor.getCapacidadeMax());
            stmt.setInt(3, setor.getBoxesOcupados());
            stmt.executeUpdate();

            try (var rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    setor.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir setor no MySQL", e);
        }
    }

    // [U] - ATUALIZAR QUANTIDADE DE BOXES
    public void atualizar(Setor setor) {
        String sql = "UPDATE setor SET boxes_ocupados = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, setor.getBoxesOcupados());
            stmt.setLong(2, setor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar setor no MySQL", e);
        }
    }
}
