package model;

public class SolicitacaoAgendamento {
    private int id;
    private String status;
    private Veiculo veiculo;
    private String motivoNegacao;
    
    public SolicitacaoAgendamento() {
    }
    
    public SolicitacaoAgendamento(int id, String status, Veiculo veiculo) {
        this.id = id;
        this.status = status;
        this.veiculo = veiculo;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public String getMotivoNegacao() {
        return motivoNegacao;
    }
    
    public void setMotivoNegacao(String motivoNegacao) {
        this.motivoNegacao = motivoNegacao;
    }
}