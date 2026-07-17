package br.edu.ifrn.vethosp.servico;

import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.repositorio.GerenciadorDeConexao;
import br.edu.ifrn.vethosp.repositorio.RepositorioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SetorServico {

    // Método para cadastrar um novo setor no banco de dados
    public void cadastrarSetor(Setor setor) throws RepositorioException {
        String sql = "INSERT INTO setor (nome, capacidade_max, boxes_ocupados) VALUES (?, ?, ?)";

        try (Connection conexao = GerenciadorDeConexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, setor.getNome());
            stmt.setInt(2, setor.getCapacidadeMax());
            stmt.setInt(3, setor.getBoxesOcupados());

            stmt.executeUpdate();

            // Recupera o ID gerado pelo MySQL
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    setor.setId(rs.getLong(1));
                }
            }

            System.out.println("Setor cadastrado com sucesso no banco de dados: " + setor.getNome());

        } catch (SQLException e) {
            throw new RepositorioException("Erro ao cadastrar o setor no banco de dados.", e);
        }
    }

    // Método para listar todos os setores cadastrados
    public List<Setor> listarTodos() throws RepositorioException {
        List<Setor> setores = new ArrayList<>();
        String sql = "SELECT * FROM setor";

        try (Connection conexao = GerenciadorDeConexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Setor setor = new Setor();
                setor.setId(rs.getLong("id"));
                setor.setNome(rs.getString("nome"));
                setor.setCapacidadeMax(rs.getInt("capacidade_max"));
                setor.setBoxesOcupados(rs.getInt("boxes_ocupados"));
                setores.add(setor);
            }

        } catch (SQLException e) {
            throw new RepositorioException("Erro ao buscar a lista de setores no banco de dados.", e);
        }

        return setores;
    }
}
