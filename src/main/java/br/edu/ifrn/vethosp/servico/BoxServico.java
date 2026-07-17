package br.edu.ifrn.vethosp.servico;

import br.edu.ifrn.vethosp.modelo.Box;
import br.edu.ifrn.vethosp.repositorio.GerenciadorDeConexao;
import br.edu.ifrn.vethosp.repositorio.RepositorioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoxServico {

    // Método para cadastrar um novo Box associado a um Setor
    public void cadastrarBox(Box box) throws RepositorioException {
        String sql = "INSERT INTO box (identificador, status, setor_id) VALUES (?, ?, ?)";

        try (Connection conexao = GerenciadorDeConexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Se o atributo na sua classe Box ainda se chamar getNumero(), 
            // transformamos ele em texto usando String.valueOf() para gravar na coluna 'identificador'
            stmt.setString(1, String.valueOf(box.getNumero()));
            stmt.setString(2, box.getStatus().name()); 
            stmt.setLong(3, box.getSetor().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    box.setId(rs.getLong(1));
                }
            }

            System.out.println("Box cadastrado com sucesso no banco de dados. Identificador: " + box.getNumero());

        } catch (SQLException e) {
            throw new RepositorioException("Erro ao cadastrar o box no banco de dados.", e);
        }
    }

    // Método para listar todos os boxes cadastrados
    public List<Box> listarTodos() throws RepositorioException {
        List<Box> boxes = new ArrayList<>();
        String sql = "SELECT * FROM box";

        try (Connection conexao = GerenciadorDeConexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Box box = new Box();
                box.setId(rs.getLong("id"));
                
                // Converte de volta o identificador do banco para o número na classe Java
                try {
                    box.setNumero(Integer.parseInt(rs.getString("identificador")));
                } catch (NumberFormatException e) {
                    // Evita que o programa quebre se o identificador não for apenas números
                    box.setNumero(0); 
                }
                
                boxes.add(box);
            }

        } catch (SQLException e) {
            throw new RepositorioException("Erro ao buscar a lista de boxes no banco de dados.", e);
        }

        return boxes;
    }
}
