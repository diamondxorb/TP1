package model;

public class Vistoriador extends Pessoa {
    private String cadastroDetran;
    private String assinatura;

    //Construtor
    public Vistoriador(String nome, String cpf, String dataDeNascimento, String endereco, String email, String celular, String senha, String cadastroDetran, String assinatura) {
        super(nome, cpf, dataDeNascimento, endereco, email, celular, senha);
        this.cadastroDetran = cadastroDetran;
        this.assinatura = assinatura;
    }

    //Getters
    public String getCadastroDetran() {return cadastroDetran;}
    public String getAssinatura() {return assinatura;}

    //Setters
    public void setCadastroDetran(String cadastroDetran) {this.cadastroDetran = cadastroDetran;}
    public void setAssinatura(String assinatura) {this.assinatura = assinatura;}

    /*IMPLEMENTAR
    public void criaLaudo(Laudo laudo) {
        ;
    }
     */
}
