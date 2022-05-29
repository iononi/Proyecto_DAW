package model;

import java.io.Serializable;

public class ReporteAnonimo implements Serializable {

    private int folio;
    private String nombre;
    private String apellidop, apellidom;
    private String telefono, extension;
    private Direccion dir;
    private String correo;
    private int fkTipoResiduo, fkMetodoPago;
    private boolean pagado;
    private short fk_estado;

    public ReporteAnonimo() {

    }

    public ReporteAnonimo(int folio, String nombre, String apellidop, String apellidom, String telefono, String extension,
                          Direccion dir, int fkTipoResiduo, int fkMetodoPago, boolean pagado, short fk_estado, String correo) {
        setFolio(folio);
        setNombre(nombre);
        setApellidop(apellidop);
        setApellidom(apellidom);
        setTelefono(telefono);
        setExtension(extension);
        setDir(dir);
        setFkTipoResiduo(fkTipoResiduo);
        setFkMetodoPago(fkMetodoPago);
        setPagado(pagado);
        setFk_estado(fk_estado);
        setCorreo(correo);
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null)
            this.nombre = (nombre.matches("[A-Za-z .,-]+")) ? nombre : "";
        else
            this.nombre = "";
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        if (apellidop != null)
            this.apellidop = (apellidop.matches("[A-Za-z .,-]+")) ? apellidop : "";
        else
            this.apellidop = "";
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        if (apellidom != null)
            this.apellidom = (apellidom.matches("[A-Za-z .,-]+")) ? apellidom : "";
        else
            this.apellidom = "";
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null)
            this.telefono = (telefono.matches("[\\d]+")) ? telefono : "";
        else
            this.telefono = "";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo != null)
            this.correo = (correo.matches("[\\w.-]*@[a-z.]*")) ? correo : "";
        else
            this.correo = "";
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        if (extension != null)
            this.extension = (extension.matches("[\\d]+")) ? extension : "";
        else
            this.extension = "";
    }

    public Direccion getDir() {
        return dir;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }

    public int getFkTipoResiduo() {
        return fkTipoResiduo;
    }

    public void setFkTipoResiduo(int fkTipoResiduo) {
        this.fkTipoResiduo = fkTipoResiduo;
    }

    public int getFkMetodoPago() {
        return fkMetodoPago;
    }

    public void setFkMetodoPago(int fkMetodoPago) {
        this.fkMetodoPago = fkMetodoPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public short getFk_estado() {
        return fk_estado;
    }

    public void setFk_estado(short fk_estado) {
        this.fk_estado = fk_estado;
    }

    @Override
    public String toString() {
        return String.format("Folio: %d\n\nDatos Usuario:\n\n\tNombre: %s\n\tApellidos: %s, %s\n\tTelefono: (%s) %s\n\t" +
                "Tipo Residuo: %d\n\tMetodo Pago: %d\n\tPagado: %b", folio, nombre, apellidop, apellidom, extension, telefono,
                fkTipoResiduo, fkMetodoPago, pagado);
    }
}
