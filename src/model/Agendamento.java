package model;

public class Agendamento {
    private int id;
    private String data;
    private String horario;
    private String motivoAgendamento;
    private Atendente atendente;

    public Agendamento(int id, String data, String horario,String motivoAgendamento, Atendente atendente) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.motivoAgendamento = motivoAgendamento;
        this.atendente = atendente;
    }

    public Agendamento(String data, String horario,String motivoAgendamento, Atendente atendente) {
        this.data = data;
        this.horario = horario;
        this.motivoAgendamento = motivoAgendamento;
        this.atendente = atendente;
    }




    public Atendente getAtendente() {
        return atendente;
    }


    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getMotivoAgendamento() {
        return motivoAgendamento;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotivoAgendamento(String motivoAgendamento) {
        this.motivoAgendamento = motivoAgendamento;
    }
}
