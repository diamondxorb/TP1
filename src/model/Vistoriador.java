package model;

import java.time.LocalDate;

public class Vistoriador extends Pessoa {
    private String cadastroDetran;
    private String senha;

    //Construtor
    public Vistoriador(String nome, String cpf, LocalDate dataDeNascimento, String endereco, String email, String celular, String cadastroDetran, String senha) {
        super(nome, cpf, dataDeNascimento, endereco, email, celular);
        this.cadastroDetran = cadastroDetran;
        this.senha = senha;
    }

    //Getters
    public String getCadastroDetran() {return cadastroDetran;}
    public String getSenha() {return senha;}

    //Setters
    public void setCadastroDetran(String cadastroDetran) {this.cadastroDetran = cadastroDetran;}
    public void setSenha(String senha) {this.senha = senha;}
}
