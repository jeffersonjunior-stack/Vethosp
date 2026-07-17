package br.edu.ifrn.vethosp.modelo;

/**
 * Entidade que representa um box para animais.
 */
public class Box {

    private Long id;
    private String identificador;
    private StatusBox status;
    private Setor setor;

    public Box() {
        this.status = StatusBox.LIVRE;
    }

    public Box(String identificador, Setor setor) {
        this.identificador = identificador;
        this.setor = setor;
        this.status = StatusBox.LIVRE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public StatusBox getStatus() { return status; }
    public void setStatus(StatusBox status) { this.status = status; }

    public Setor getSetor() { return setor; }
    public void setSetor(Setor setor) { this.setor = setor; }

    

    public boolean isLivre() {
        return status == StatusBox.LIVRE;
    }

    @Override
    public String toString() {
        return "Box{id=" + id + ", identificador='" + identificador + "', status=" + status + "}";
    }
}
