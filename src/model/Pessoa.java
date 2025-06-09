package model;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String endereco;
    private String email;
    private String celular;

    //Construtor
    public Pessoa(String nome, String cpf, LocalDate dataDeNascimento, String endereco, String email, String celular) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.email = email;
        this.celular = celular;
    }

    //Getters
    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
    public LocalDate getDataDeNascimento() {return dataDeNascimento;}
    public String getEndereco() {return endereco;}
    public String getEmail() {return email;}
    public String getCelular() {return celular;}

    //Setters
    public void setNome(String nome) {this.nome = nome;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setDataDeNascimento(LocalDate dataDeNascimento) {this.dataDeNascimento = dataDeNascimento;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setEmail(String email) {this.email = email;}
    public void setCelular(String celular) {this.celular = celular;}
}
