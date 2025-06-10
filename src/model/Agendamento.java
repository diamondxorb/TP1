package model;

import java.util.Date;

public class Agendamento {
    private int id;
    private Date dataHora;
    private String cliente;
    private String local;
    private String status;
    private Veiculo veiculo;

    //Construtor
    public Agendamento(int id, Date dataHora, String cliente, String local, String status, Veiculo veiculo) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.local = local;
        this.status = status;
        this.veiculo = veiculo;
    }

    public String getDataHoraFormatada() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataHora);
    }

    //Getters
    public int getId() {return id;}
    public Date getDataHora() {return dataHora;}
    public String getCliente() {return cliente;}
    public String getLocal() {return local;}
    public String getStatus() {return status;}
    public Veiculo getVeiculo() {return veiculo;}

    //Setters
    public void setDataHora(Date dataHora) {this.dataHora = dataHora;}
    public void setCliente(String cliente) {this.cliente = cliente;}
    public void setLocal(String local) {this.local = local;}
    public void setStatus(String status) {this.status = status;}
}
