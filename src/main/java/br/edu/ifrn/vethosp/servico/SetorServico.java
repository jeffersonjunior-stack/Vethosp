package br.edu.ifrn.vethosp.servico;

import br.edu.ifrn.vethosp.modelo.Setor;

public class SetorServico {
    public void cadastrarSetor(Setor setor) {
        System.out.println("Setor cadastrado: " + setor.getNome());
    }
}
