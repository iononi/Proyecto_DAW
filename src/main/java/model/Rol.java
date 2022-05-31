package model;

import java.io.Serializable;

public class Rol implements Serializable {
    private short rolID;
    private String nombreRol;

    public Rol() {

    }

    public Rol(short rolID, String nombreRol) {
        setRolID(rolID);
        setNombreRol(nombreRol);
    }

    public short getRolID() {
        return rolID;
    }

    public void setRolID(short rolID) {
        this.rolID = rolID;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return String.format("Rol ID: %d\nRol: %s\n", rolID, nombreRol);
    }
}
