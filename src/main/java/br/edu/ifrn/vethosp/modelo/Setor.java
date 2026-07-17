package br.edu.ifrn.vethosp.modelo;

/**
 * Entidade que representa um setor da clínica veterinária.
 */
public class Setor {

    private Long id;
    private String nome;
    private int capacidadeMax;
    private int boxesOcupados;

    public Setor() {}

    public Setor(String nome, int capacidadeMax) {
        this(nome, capacidadeMax, 0);
    }

    public Setor(String nome, int capacidadeMax, int boxesOcupados) {
        this.nome = nome;
        this.capacidadeMax = capacidadeMax;
        this.boxesOcupados = boxesOcupados;
    }

    public int getBoxesDisponiveis() {
        return capacidadeMax - boxesOcupados;
    }

    public boolean isLotado() {
        return boxesOcupados >= capacidadeMax;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidadeMax() { return capacidadeMax; }
    public void setCapacidadeMax(int capacidadeMax) { this.capacidadeMax = capacidadeMax; }

    public int getBoxesOcupados() { return boxesOcupados; }
    public void setBoxesOcupados(int boxesOcupados) { this.boxesOcupados = boxesOcupados; }

    @Override
    public String toString() {
        return "Setor{id=" + id + ", nome='" + nome + "', capacidade=" + capacidadeMax + "}";
    }
}
