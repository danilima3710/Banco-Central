package br.edu.ifsc.BancoCentral.model.dto;

public class InformacaoTransferenciaDto {

    private String CVV;
    private int Installments;
    private String Numero;
    private double Valor;
    private String Data;
    private String Senha;

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public int getInstallments() {
        return Installments;
    }

    public void setInstallments(int installments) {
        Installments = installments;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
