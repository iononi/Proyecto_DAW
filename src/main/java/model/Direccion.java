package model;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Direccion implements SQLData {

    private String codigoPostal;
    private String colonia;
    private String calle;
    private String referencias;
    private short numeroInterior;
    private short numeroExterior;
    private String ciudad;
    private String municipio;
    private String estado;
    private String sql_type;

    public Direccion() {
    }

    public Direccion(String codigoPostal, String colonia, String calle, String referencias, short numeroInterior,
                     short numeroExterior, String ciudad, String municipio, String estado) {
        setCodigoPostal(codigoPostal);
        setColonia(colonia);
        setCalle(calle);
        setReferencias(referencias);
        setNumeroInterior(numeroInterior);
        setNumeroExterior(numeroExterior);
        setCiudad(ciudad);
        setMunicipio(municipio);
        setEstado(estado);
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        if (codigoPostal != null)
            this.codigoPostal = (codigoPostal.matches("[\\d]{5}")) ? codigoPostal : "";
        else
            this.codigoPostal = "";
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = (colonia != null) ? colonia : "";
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = (calle != null) ? calle : "";
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = (referencias != null) ? referencias : "";
    }

    public short getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(short numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public short getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(short numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = (ciudad != null) ? ciudad : "";
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = (municipio != null) ? municipio : "";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = (estado != null) ? estado : "";
    }

    @Override
    public String toString() {
        return String.format("Codigo Postal: %s\nColonia: %s\nCalle: %s\nReferencias: %s\nNumero Interior: #%d\n" +
                "Numero Exterior: #%d\nCiudad: %s\nMunicipio: %s\nEstado: %s", codigoPostal, colonia, calle, referencias,
                numeroInterior, numeroExterior, ciudad, municipio, estado);
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        sql_type = typeName;
        codigoPostal = stream.readString();
        colonia = stream.readString();
        calle = stream.readString();
        referencias = stream.readString();
        numeroInterior = stream.readShort();
        numeroExterior = stream.readShort();
        ciudad = stream.readString();
        municipio = stream.readString();
        estado = stream.readString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(codigoPostal);
        stream.writeString(colonia);
        stream.writeString(calle);
        stream.writeString(referencias);
        stream.writeShort(numeroInterior);
        stream.writeShort(numeroExterior);
        stream.writeString(ciudad);
        stream.writeString(municipio);
        stream.writeString(estado);
    }
}
