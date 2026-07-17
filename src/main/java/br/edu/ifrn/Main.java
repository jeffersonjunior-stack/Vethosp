package br.edu.ifrn;

import br.edu.ifrn.vethosp.modelo.Setor;
import br.edu.ifrn.vethosp.modelo.Box;
import br.edu.ifrn.vethosp.modelo.StatusBox;
import br.edu.ifrn.vethosp.repositorio.GerenciadorDeConexao;

/**
 * Demonstração do Sistema VetHosp.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema VetHosp - Clínica Veterinária ===");
        System.out.println("Banco configurado: " + GerenciadorDeConexao.getUrl());

        System.out.println("\nProjeto carregado com sucesso!");
        System.out.println("Para teste completo, execute no computador com Maven e MySQL.");
    }
}
