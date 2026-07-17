package br.edu.ifrn.vethosp.servico;

import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.repositorio.SetorRepositorio;
import br.edu.ifrn.vethosp.repositorio.RepositorioException;

import java.util.List;

public class SetorServico {

    private SetorRepositorio setorRepositorio = new SetorRepositorio();

    // Método para cadastrar um novo setor (com validação de negócio antes de salvar no banco)
    public void cadastrarSetor(Setor setor) throws RepositorioException {
        if (setor.getCapacidadeMax() <= 0) {
            throw new IllegalArgumentException("A capacidade máxima deve ser maior que zero.");
        }
        
        // Agora o serviço apenas "manda" o repositório salvar
        setorRepositorio.inserir(setor);
        
        System.out.println("Setor cadastrado com sucesso: " + setor.getNome());
    }

    // Método para atualizar a contagem de boxes ocupados
    public void atualizarSetor(Setor setor) throws RepositorioException {
        setorRepositorio.atualizar(setor);
    }

    // Método para listar todos os setores cadastrados
    public List<Setor> listarTodos() throws RepositorioException {
        // Agora o serviço apenas "pede" a lista para o repositório
        return setorRepositorio.selecionarTodos();
    }
}
