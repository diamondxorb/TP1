package model;

public class Proprietario extends Pessoa {
    private int documentoVeiculo;
    private boolean isPago;

    //Construtor
    public Proprietario(String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha) {
        super(nome, cpf, dataDeNascimento, endereco, email, celular, senha);
    }

    //Getters
    public int getDocumentoVeiculo() {return documentoVeiculo;}
    public boolean isPago() {return isPago;}

    //Setters
    public void setPago(boolean pago) {isPago = pago;}
    public void setDocumentoVeiculo(int documentoVeiculo) {}

    /*IMPLEMENTAR
    public void criaLaudo(Laudo laudo) {
        ;
    }
     */
}
