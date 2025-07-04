package model;

public class Vistoriador extends Pessoa {

    // Construtor com ID
    public Vistoriador(int id, String nome, String cpf, String dataNascimento,
                       String endereco, String email, String celular) {
        super(id, nome, cpf, dataNascimento, endereco, email, celular);
    }

    // Construtor sem ID
    public Vistoriador(String nome, String cpf, String dataNascimento,
                       String endereco, String email, String celular) {
        super(nome, cpf, dataNascimento, endereco, email, celular);
    }

    // Construtor vazio
    public Vistoriador() {
        super();
    }
}
