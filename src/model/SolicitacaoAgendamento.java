package model;

public class SolicitacaoAgendamento {
    private String local;
    private String status; // "Pendente", "Aceito", "Negado"
    private String motivoNegacao;
    private String motivo;
    private Veiculo veiculo;

    public SolicitacaoAgendamento(String local, Veiculo veiculo, String motivo) {
        this.local = local;
        this.veiculo = veiculo;
        this.motivo = motivo;
        this.status = "Pendente";
    }

    public String getLocal() {return local;}
    public String getStatus() {return status;}
    public Veiculo getVeiculo() {return veiculo;}
    public String getMotivo() {return motivo;}
    public String getMotivoNegacao() {return motivoNegacao;}

    public void setLocal(String local) {this.local = local;}
    public void setStatus(String status) {this.status = status;}
    public void setVeiculo(Veiculo veiculo) {this.veiculo = veiculo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public void setMotivoNegacao(String motivoNegacao) {this.motivoNegacao = motivoNegacao;}

    @Override
    public String toString() {
        if ("Negado".equals(status)) {
            return "Local: " + local + " | Status: Negado | Motivo: " + motivoNegacao;
        }
        return "Local: " + local + " | Status: " + status;
    }
}