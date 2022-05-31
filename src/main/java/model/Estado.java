package model;

import java.io.Serializable;

public class Estado implements Serializable {
    private short estadoID;
    private String estado;

    public Estado() {

    }

    public Estado(short estadoID, String estado) {
        setEstadoID(estadoID);
        setEstado(estado);
    }

    public short getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(short estadoID) {
        this.estadoID = estadoID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Estado ID: %d\nEstado: %s\n", estadoID, estado);
    }
}
