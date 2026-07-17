package br.edu.ifrn.vethosp.servico;

import br.edu.ifrn.vethosp.modelo.Box;

public class BoxServico {
    public Box cadastrarBox(Long setorId, String identificador) {
        System.out.println("Box " + identificador + " cadastrado.");
        return new Box(identificador, null);
    }
}
