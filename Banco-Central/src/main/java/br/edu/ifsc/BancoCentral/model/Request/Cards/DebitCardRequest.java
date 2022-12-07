package br.edu.ifsc.BancoCentral.model.Request.Cards;

public class DebitCardRequest  extends CardRequest{
    public String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
