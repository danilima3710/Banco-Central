package br.edu.ifsc.BancoCentral.model.filtro;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

public class AlteraStatusTerminal {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
