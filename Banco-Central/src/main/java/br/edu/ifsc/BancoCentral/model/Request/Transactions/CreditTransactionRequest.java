package br.edu.ifsc.BancoCentral.model.Request.Transactions;

import br.edu.ifsc.BancoCentral.model.Request.Cards.CreditCardRequest;

import java.util.UUID;

public class CreditTransactionRequest {
    public CreditCardRequest Card;
    public double Value;
    public int Installments;
    public UUID idTerminal;
    public String nome;

    public CreditCardRequest getCard() {
        return Card;
    }

    public void setCard(CreditCardRequest card) {
        Card = card;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public int getInstallments() {
        return Installments;
    }

    public void setInstallments(int installments) {
        Installments = installments;
    }

    public UUID getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(UUID idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
