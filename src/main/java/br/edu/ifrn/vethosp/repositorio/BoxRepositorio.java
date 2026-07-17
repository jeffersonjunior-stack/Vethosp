package br.edu.ifrn.vethosp.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.vethosp.modelo.Box;
import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.modelo.StatusBox;

public class BoxRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    public void inserir(Box box) {
        String sql = "INSERT INTO box (identificador, status, setor_id) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, box.getIdentificador());
            stmt.setString(2, box.getStatus().name());
            stmt.setLong(3, box.getSetor().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    box.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir box no MySQL", e);
        }
    }

    // LISTAR TODOS USANDO INNER JOIN PARA POPULAR O SETOR
    public List<Box> selecionarTodos() {
        List<Box> boxes = new ArrayList<>();
        String sql = "SELECT b.id AS box_id, b.identificador, b.status, " +
                     "s.id AS setor_id, s.nome AS setor_nome, s.capacidade_max, s.boxes_ocupados " +
                     "FROM box b " +
                     "INNER JOIN setor s ON b.setor_id = s.id";

        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // 1. Instancia o Setor e popula os dados dele
                Setor setor = new Setor();
                setor.setId(rs.getLong("setor_id"));
                setor.setNome(rs.getString("setor_nome"));
                setor.setCapacidadeMax(rs.getInt("capacidade_max"));
                setor.setBoxesOcupados(rs.getInt("boxes_ocupados"));

                // 2. Instancia o Box e associa o setor criado acima
                Box box = new Box();
                box.setId(rs.getLong("box_id"));
                box.setIdentificador(rs.getString("identificador"));
                box.setStatus(StatusBox.valueOf(rs.getString("status").toUpperCase()));
                box.setSetor(setor); // <--- ASSOCIAÇÃO COMPLETADA!

                boxes.add(box);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar boxes com setores do MySQL", e);
        }
        return boxes;
    }
}
