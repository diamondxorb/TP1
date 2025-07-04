package model;

public class Veiculo {
    private String placa;
    private String categoria;
    private String marca;
    private String cor;
    private String anoFabricacao;

    //Construtor
    public Veiculo(String placa, String categoria, String marca, String cor, String anoFabricacao) {
        this.placa = placa;
        this.categoria = categoria;
        this.marca = marca;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
    }

    //Getters
    public String getPlaca() {return placa;}
    public String getCategoria() {return categoria;}
    public String getMarca() {return marca;}
    public String getCor() {return cor;}
    public String getAnoFabricacao() {return anoFabricacao;}

    //Setters
    public void setPlaca(String placa) {this.placa = placa;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setMarca(String marca) {this.marca = marca;}
    public void setCor(String cor) {this.cor = cor;}
    public void setAnoFabricacao(String anoFabricacao) {this.anoFabricacao = anoFabricacao;}
}
