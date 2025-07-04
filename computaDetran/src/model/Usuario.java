package model;

public class Usuario {
    private String login;
    private String senha;
    private String perfil;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {return login;}
    public String getSenha() {return senha;}
    public String getPerfil() {return perfil;}

    public void setLogin(String login) {this.login = login;}
    public void setSenha(String senha) {this.senha = senha;}
    public void setPerfil(String perfil) {this.perfil = perfil;}
}
