package model;

public class Atendente extends Pessoa {
    private int numeroIdentificacao;

    //Construtor
    public Atendente(String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha, int numeroIdentificacao) {
        super(nome, cpf, dataDeNascimento, endereco, email, celular, senha);
        this.numeroIdentificacao = numeroIdentificacao;
    }

    //Getters
    public int getNumeroIdentificacao() {return numeroIdentificacao;}

    //Setters
    public void setNumeroIdentificacao(int numeroIdentificacao) {this.numeroIdentificacao = numeroIdentificacao;}

    /* IMPLEMENTAR
    public void criaAgendamento(Agendamento agendamento) {
        ;
    }
    public boolean verificaVeiculo(Proprietario proprietario) {
        return proprietario.getIsPago();
    }
     */
}
