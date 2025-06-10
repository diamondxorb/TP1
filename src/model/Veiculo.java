// src/model/Veiculo.java
package model;

public class Veiculo {
    private String placa;
    private String modelo;
    private Proprietario proprietario;
    private boolean documentoPago;

    public Veiculo(String placa, String modelo, Proprietario proprietario, boolean documentoPago) {
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.documentoPago = documentoPago;
    }

    // Getters (sem setters para imutabilidade)
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public Proprietario getProprietario() { return proprietario; }
    public boolean isDocumentoPago() { return documentoPago; }
}