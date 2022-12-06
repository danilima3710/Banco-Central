package br.edu.ifsc.BancoCentral.model;

import java.util.UUID;

public class AlteraStatusTerminal {
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
