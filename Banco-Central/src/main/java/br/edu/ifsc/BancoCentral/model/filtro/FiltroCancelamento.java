package br.edu.ifsc.BancoCentral.model.filtro;

import java.util.UUID;

public class FiltroCancelamento {
    private UUID idBacen;
    private UUID idTerminal;
    private UUID idCentral;

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

    public UUID getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(UUID idCentral) {
        this.idCentral = idCentral;
    }
}
