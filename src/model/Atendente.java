package model;

public class Atendente extends Pessoa {
    private int numeroIdentificacao;
    private String senhaHash;

    // Construtor COMPLETO com ID (para editar ou listar)
    public Atendente(int id, String nome, String cpf, String dataNascimento,
                     String endereco, String email, String celular,
                     int numeroIdentificacao, String senhaHash) {
        super(id, nome, cpf, dataNascimento, endereco, email, celular);
        this.numeroIdentificacao = numeroIdentificacao;
        this.senhaHash = senhaHash;
    }

    // Construtor SEM ID (para cadastro)
    public Atendente(String nome, String cpf, String dataNascimento,
                     String endereco, String email, String celular,
                     int numeroIdentificacao, String senhaHash) {
        super(nome, cpf, dataNascimento, endereco, email, celular);
        this.numeroIdentificacao = numeroIdentificacao;
        this.senhaHash = senhaHash;
    }

    // Construtor VAZIO (útil para editar)
    public Atendente() {
        super();
    }

    // Getters e Setters
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

    // Você pode sobrescrever toString() se quiser debug
    @Override
    public String toString() {
        return "Atendente{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", numeroIdentificacao=" + numeroIdentificacao +
                '}';
    }
}
