package br.edu.ifrn.vethosp.servico;

import java.util.List;
import br.edu.ifrn.vethosp.modelo.Box;
import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.repositorio.BoxRepositorio;
import br.edu.ifrn.vethosp.repositorio.RepositorioException;

public class BoxServico {

    private BoxRepositorio boxRepositorio = new BoxRepositorio();
    private SetorServico setorServico = new SetorServico();

    // Regra de negócio: Cadastrar Box e incrementar o contador do Setor
    public void cadastrarBox(Box box) throws RepositorioException {
        Setor setor = box.getSetor();

        if (setor == null) {
            throw new IllegalArgumentException("O box precisa estar associado a um setor válido.");
        }

        if (setor.isLotado()) {
            throw new IllegalStateException("Não é possível adicionar o box. O setor já está lotado!");
        }

        // 1. Salva o box no banco de dados usando o repositório
        boxRepositorio.inserir(box);

        // 2. Atualiza a contagem de boxes ocupados na memória do Java
        setor.setBoxesOcupados(setor.getBoxesOcupados() + 1);
        
        // 3. Persiste a alteração do setor no banco de dados
        setorServico.atualizarSetor(setor);
        
        System.out.println("Box cadastrado com sucesso: " + box.getIdentificador());
    }

    // Retorna todos os boxes com seus respectivos setores já populados
    public List<Box> listarTodos() throws RepositorioException {
        return boxRepositorio.selecionarTodos();
    }
}
