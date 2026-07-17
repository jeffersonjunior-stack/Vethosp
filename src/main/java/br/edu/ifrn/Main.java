package br.edu.ifrn;

import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.modelo.Box;
import br.edu.ifrn.vethosp.modelo.StatusBox;
import br.edu.ifrn.vethosp.repositorio.GerenciadorDeConexao;
import br.edu.ifrn.vethosp.servico.SetorServico;
import br.edu.ifrn.vethosp.servico.BoxServico;
import br.edu.ifrn.vethosp.repositorio.RepositorioException;

import java.util.List;

/**
 * Demonstração do Sistema VetHosp com salvamento real no Banco de Dados.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema VetHosp - Clínica Veterinária ===");
        System.out.println("Banco configurado: " + GerenciadorDeConexao.getUrl());
        System.out.println("--------------------------------------------------\n");

        SetorServico setorServico = new SetorServico();
        BoxServico boxServico = new BoxServico();

        try {
            System.out.println("[Teste 1] Cadastrando um novo setor...");
            Setor novoSetor = new Setor("Urgência e Emergência", 5);
            setorServico.cadastrarSetor(novoSetor);
            // O id agora foi gerado pelo banco de dados!
            System.out.println("Setor cadastrado com ID: " + novoSetor.getId() + "\n");

            System.out.println("[Teste 2] Cadastrando um Box para este setor...");
            Box novoBox = new Box();
            novoBox.setIdentificador("Box 101");
            novoBox.setStatus(StatusBox.LIVRE);
            novoBox.setSetor(novoSetor);
            boxServico.cadastrarBox(novoBox);
            System.out.println("Box cadastrado com ID: " + novoBox.getId() + "\n");

            System.out.println("[Teste 3] Listando setores cadastrados no banco:");
            List<Setor> todosSetores = setorServico.listarTodos();
            for (Setor s : todosSetores) {
                System.out.println(" - " + s.getNome() + " (Capacidade: " + s.getCapacidadeMax() + ")");
            }

            System.out.println("\n🎉 PROJETO INTEGRADO COM SUCESSO AO BANCO DE DADOS!");

        } catch (RepositorioException e) {
            System.err.println("\n❌ ERRO NA INTEGRAÇÃO COM O BANCO DE DADOS:");
            System.err.println("Motivo: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Detalhe técnico: " + e.getCause().getMessage());
            }
            System.err.println("\nVerifique se o seu MySQL local está ativo e se a senha do root no GerenciadorDeConexao está correta!");
        }
    }
}