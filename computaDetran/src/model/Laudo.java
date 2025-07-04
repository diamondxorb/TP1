package model;

import java.util.Date;

public class Laudo {
    private String status;
    private String motivo;
    private Date dataEmissao;
    private Vistoriador vistoriador;

    //Construtor
    public Laudo(String status, String motivo, Date dataEmissao, Vistoriador vistoriador) {
        this.status = status;
        this.motivo = motivo;
        this.dataEmissao = dataEmissao;
        this.vistoriador = vistoriador;
    }

    public String getDataEmissaoFormatada() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataEmissao);
    }

    //Getters
    public String getStatus() {return status;}
    public String getMotivo() {return motivo;}
    public Vistoriador getVistoriador() {return vistoriador;}

    //Setters
    public void setStatus(String status) {this.status = status;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public void setDataEmissao(Date dataEmissao) {this.dataEmissao = dataEmissao;}

    public void mostrarLaudo() {
        System.out.println("Status: " + this.status);
        System.out.println("Motivo: " + this.motivo);
        System.out.println("Data de emissão: " + this.dataEmissao);
        System.out.println("Vistoriador: " + this.vistoriador);
    }
}
