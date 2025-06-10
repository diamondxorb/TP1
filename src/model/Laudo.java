package model;

import java.util.Date;

public class Laudo {
    private String status;
    private String motivo;
    private Date dataEmissao;
    private Vistoriador vistoriador;
    private Proprietario proprietario;
    private Veiculo veiculo;

    //Construtor
    public Laudo(String status, String motivo, Date dataEmissao, Vistoriador vistoriador, Proprietario proprietario, Veiculo veiculo) {
        this.status = status;
        this.motivo = motivo;
        this.dataEmissao = dataEmissao;
        this.vistoriador = vistoriador;
        this.proprietario = proprietario;
        this.veiculo = veiculo;
    }

    public String getDataEmissaoFormatada() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataEmissao);
    }

    //Getters
    public String getStatus() {return status;}
    public String getMotivo() {return motivo;}
    public Vistoriador getVistoriador() {return vistoriador;}
    public Proprietario getProprietario() {return proprietario;}
    public Veiculo getVeiculo() {return veiculo;}

    //Setters
    public void setStatus(String status) {this.status = status;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public void setDataEmissao(Date dataEmissao) {this.dataEmissao = dataEmissao;}

    @Override
    public String toString() {
        return "Status: " + status + "\n" +
                "Motivo: " + motivo + "\n" +
                "Data de Emissão: " + getDataEmissaoFormatada() + "\n" +
                "Vistoriador: " + vistoriador.getNome() + "\n" +
                "Proprietário: " + proprietario.getNome() + "\n" +
                "Veículo: " + veiculo.getModelo() + " - " + veiculo.getPlaca();
    }
}
