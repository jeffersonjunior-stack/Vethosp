package br.edu.ifrn.vethosp.repositorio;

public class RepositorioException extends RuntimeException {
    public RepositorioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
