package model;

public class Agendamento {
    private int id;
    private String data;
    private String horario;
    private String motivoAgendamento;
    private Atendente atendente;
    private Vistoriador vistoriador;

    // Construtor padrão vazio
    public Agendamento() {
    }

    // Construtor para SELECT com ID
    public Agendamento(int id, String data, String horario, String motivoAgendamento,
                       Atendente atendente, Vistoriador vistoriador) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.motivoAgendamento = motivoAgendamento;
        this.atendente = atendente;
        this.vistoriador = vistoriador;
    }

    // Construtor para INSERT sem ID
    public Agendamento(String data, String horario, String motivoAgendamento,
                       Atendente atendente, Vistoriador vistoriador) {
        this.data = data;
        this.horario = horario;
        this.motivoAgendamento = motivoAgendamento;
        this.atendente = atendente;
        this.vistoriador = vistoriador;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getMotivoAgendamento() { return motivoAgendamento; }
    public void setMotivoAgendamento(String motivoAgendamento) { this.motivoAgendamento = motivoAgendamento; }

    public Atendente getAtendente() { return atendente; }
    public void setAtendente(Atendente atendente) { this.atendente = atendente; }

    public Vistoriador getVistoriador() { return vistoriador; }
    public void setVistoriador(Vistoriador vistoriador) { this.vistoriador = vistoriador; }
}
