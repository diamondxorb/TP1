package model;

public class SolicitacaoAgendamento {
    private String local;
    private String status; // "Pendente", "Aceito", "Negado"
    private String motivoNegacao;

    public SolicitacaoAgendamento(String local) {
        this.local = local;
        this.status = "Pendente";
    }

    public String getLocal() {return local;}
    public String getStatus() {return status;}
    public String getMotivoNegacao() {return motivoNegacao;}

    public void setLocal(String local) {this.local = local;}
    public void setStatus(String status) {this.status = status;}
    public void setMotivoNegacao(String motivoNegacao) {this.motivoNegacao = motivoNegacao;}

    @Override
    public String toString() {
        if ("Negado".equals(status)) {
            return "Local: " + local + " | Status: Negado | Motivo: " + motivoNegacao;
        }
        return "Local: " + local + " | Status: " + status;
    }
}