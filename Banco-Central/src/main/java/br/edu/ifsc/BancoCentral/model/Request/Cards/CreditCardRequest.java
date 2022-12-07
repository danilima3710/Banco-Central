package br.edu.ifsc.BancoCentral.model.Request.Cards;

public class CreditCardRequest extends CardRequest {
    public String CVV;

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
