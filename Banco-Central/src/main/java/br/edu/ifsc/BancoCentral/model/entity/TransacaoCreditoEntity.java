package br.edu.ifsc.BancoCentral.model.entity;

import br.edu.ifsc.BancoCentral.model.Request.Transactions.CreditTransactionRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TransacaoCreditoEntity {

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public UUID getIdBacen() {
        return idBacen;
    }

    public void setIdBacen(UUID idBacen) {
        this.idBacen = idBacen;
    }

    public boolean isTransacaoOK() {
        return transacaoOK;
    }

    public UUID getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(UUID idTerminal) {
        this.idTerminal = idTerminal;
    }

    public void setTransacaoOK(boolean transacaoOK) {
        this.transacaoOK = transacaoOK;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isTransacaoCancelada() {
        return transacaoCancelada;
    }

    public void setTransacaoCancelada(boolean transacaoCancelada) {
        this.transacaoCancelada = transacaoCancelada;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private double value;
    private int installments;
    private String numero;
    private String CVV;
    private UUID idBacen;
    private UUID idTerminal;
    private boolean transacaoOK;
    private String data;
    private boolean transacaoCancelada;
}
