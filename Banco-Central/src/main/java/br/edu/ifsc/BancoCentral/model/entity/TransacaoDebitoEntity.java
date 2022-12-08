package br.edu.ifsc.BancoCentral.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TransacaoDebitoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private double valor;
    private String senha;
    private String numero;
    private UUID idBacen;
    private UUID idTerminal;
    private boolean transacaoOK;
    private String data;
    private boolean transacaoCancelada;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public UUID getIdBacen() {
        return idBacen;
    }

    public void setIdBacen(UUID idBacen) {
        this.idBacen = idBacen;
    }

    public UUID getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(UUID idTerminal) {
        this.idTerminal = idTerminal;
    }

    public boolean isTransacaoOK() {
        return transacaoOK;
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
}
