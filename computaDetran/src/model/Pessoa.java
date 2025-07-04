package model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private String endereco;
    private String email;
    private String celular;
    private String senha;

    //Construtor
    public Pessoa(String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
    }

    //Getters
    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
    public String getDataDeNascimento() {return dataDeNascimento;}
    public String getEndereco() {return endereco;}
    public String getEmail() {return email;}
    public String getCelular() {return celular;}
    public String getSenha() {return senha;}

    //Setters
    public void setNome(String nome) {this.nome = nome;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setDataDeNascimento(String dataDeNascimento) {this.dataDeNascimento = dataDeNascimento;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setEmail(String email) {this.email = email;}
    public void setCelular(String celular) {this.celular = celular;}
    public void setSenha(String senha) {this.senha = senha;}
}
