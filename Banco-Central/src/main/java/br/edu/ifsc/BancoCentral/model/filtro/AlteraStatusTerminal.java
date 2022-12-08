package br.edu.ifsc.BancoCentral.model.filtro;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

public class AlteraStatusTerminal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private boolean status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
