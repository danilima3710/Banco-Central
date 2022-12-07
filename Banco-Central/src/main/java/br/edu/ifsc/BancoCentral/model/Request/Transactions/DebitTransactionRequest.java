package br.edu.ifsc.BancoCentral.model.Request.Transactions;

import br.edu.ifsc.BancoCentral.model.Request.Cards.DebitCardRequest;

public class DebitTransactionRequest {
    public DebitCardRequest Card;
    public double Value;

    public DebitCardRequest getCard() {
        return Card;
    }

    public void setCard(DebitCardRequest card) {
        Card = card;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
