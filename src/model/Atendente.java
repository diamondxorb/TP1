package model;

public class Atendente extends Pessoa {
    private int numeroIdentificacao;
    private String senhaHash;

    public Atendente(int id, String nome, String cpf, String dataNascimento, String endereco, String email, String celular, int numeroIdentificacao, String senhaHash) {
        super(id, nome, cpf, dataNascimento, endereco, email, celular);
        this.numeroIdentificacao = numeroIdentificacao;
        this.senhaHash = senhaHash;
    }

    public Atendente(String nome, String cpf, String dataNascimento, String endereco, String email, String celular, int numeroIdentificacao, String senhaHash) {
        super(nome, cpf, dataNascimento, endereco, email, celular);
        this.numeroIdentificacao = numeroIdentificacao;
        this.senhaHash = senhaHash;
    }

    public int getNumeroIdentificacao() {
        return numeroIdentificacao;
    }
    public void setNumeroIdentificacao(int numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public String getSenhaHash() {
        return senhaHash;
    }
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public boolean podeAgendar(boolean isPago) {
        return isPago;
    }
}
