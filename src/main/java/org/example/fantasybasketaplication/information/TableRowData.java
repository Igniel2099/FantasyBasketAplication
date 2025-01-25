package org.example.fantasybasketaplication.information;

public class TableRowData {
    private String dineroActual;
    private String gasto;
    private String nombreJugador;

    public TableRowData(String dineroActual, String gasto, String nombreJugador) {
        this.dineroActual = dineroActual;
        this.gasto = gasto;
        this.nombreJugador = nombreJugador;
    }

    // Getters y setters
    public String getDineroActual() {
        return dineroActual;
    }

    public void setDineroActual(String dineroActual) {
        this.dineroActual = dineroActual;
    }

    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
}

