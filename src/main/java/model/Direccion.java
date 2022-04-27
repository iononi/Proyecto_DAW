package model;

public class Direccion {

    private String codigoPostal;
    private String colonia;
    private String calle;
    private String referencias;
    private short numeroInterior;
    private short numeroExterior;
    private String ciudad;
    private String municipio;
    private String estado;

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
}
