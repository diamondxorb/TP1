package model;

public class Proprietario extends Pessoa {
    private String senha;
    private int documentoVeiculo;
    private boolean isPago;


    public Proprietario(int id,String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha) {
        super(id ,nome, cpf, dataDeNascimento, endereco, email, celular);
        this.senha = senha;
    }


    public Proprietario(String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha) {
        super(nome, cpf, dataDeNascimento, endereco, email, celular);
        this.senha = senha;
    }
    public Proprietario(){
        super();
    }

    //Getters
    public String getSenha() {return senha;}
    public int getDocumentoVeiculo() {return documentoVeiculo;}
    public boolean isPago() {return isPago;}





}