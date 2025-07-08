package model;

public class Veiculo {
    private int id;
    private String placa;
    private String modelo;
    private Proprietario proprietario;
    private boolean documentoPago;


    public Veiculo() {
    }

    public Veiculo(int id, String placa, String modelo, Proprietario proprietario) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
    }
    public Veiculo(String placa, String modelo, Proprietario proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Proprietario getProprietario() { return proprietario; }
    public void setProprietario(Proprietario proprietario) { this.proprietario = proprietario; }

    public boolean isDocumentoPago() { return documentoPago; }
    public void setDocumentoPago(boolean documentoPago) { this.documentoPago = documentoPago; }

}
