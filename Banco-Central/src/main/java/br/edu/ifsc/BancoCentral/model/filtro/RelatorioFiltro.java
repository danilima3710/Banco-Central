package br.edu.ifsc.BancoCentral.model.filtro;

import java.util.UUID;

public class RelatorioFiltro {
    private String data;
    private String numeroCartao;
    private UUID idTerminal;
    private double preco;
    private boolean status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public UUID getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(UUID idTerminal) {
        this.idTerminal = idTerminal;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
