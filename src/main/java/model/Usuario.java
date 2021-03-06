package model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int clienteId;
    private String curp;
    private String rfc;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private short fk_rol;
    private String correo;
    private int contrasenia; // se guarda como hash por seguridad
    private String extension;
    private String telefono;
    private Direccion dir;

    public Usuario() {

    }

    // Main constructor
    public Usuario(int clienteId, String curp, String rfc, String nombre, String apellidop, String apellidom,
                   short fk_rol, String correo, String contrasenia, String extension, String tel, Direccion dir) {
        setClienteId(clienteId);
        setCurp(curp);
        setRfc(rfc);
        setNombre(nombre);
        setApellidop(apellidop);
        setApellidom(apellidom);
        setFk_rol(fk_rol);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setExtension(extension);
        setTelefono(tel);
        setDir(dir);
    }

    // Insertion constructor method
    public Usuario(String curp, String rfc, String nombre, String apellidop, String apellidom, short fk_rol,
                   String correo, String contrasenia, String extension, String telefono, Direccion dir) {
        setCurp(curp);
        setRfc(rfc);
        setNombre(nombre);
        setApellidop(apellidop);
        setApellidom(apellidom);
        setFk_rol(fk_rol);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setExtension(extension);
        setTelefono(telefono);
        setDir(dir);
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        if (curp != null)
            this.curp = (curp.matches("[A-Z]{4}[\\d]{6}[A-Z\\d]{8}")) ? curp : "";
        else
            this.curp = "";
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        if (rfc != null)
            this.rfc = (rfc.matches("[A-Z]{4}[\\d]{6}[A-Z]{2}[\\d]{1}")) ? rfc : "";
        else
            this.rfc = "";
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

    public short getFk_rol() {
        return fk_rol;
    }

    public void setFk_rol(short fk_rol) {
        this.fk_rol = fk_rol;
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

    public int getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if (contrasenia != null) {
            this.contrasenia = contrasenia.hashCode();
        } else {
            this.contrasenia = 0;
        }
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null)
            this.telefono = (telefono.matches("[\\d]+")) ? telefono : "";
        else
            this.telefono = "";
    }

    public Direccion getDir() {
        return dir;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }

    public boolean isAdmin() {
        return (this.fk_rol == 1);
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nCURP: %s\nRFC: %s\nNombre: %s\nApellido Paterno: %s\nApellido Materno: %s\n" +
                "Rol: %d\nCorreo: %s\nTelefono: (%s) %s\n\n== Direccion ==\n%s", clienteId, curp, rfc, nombre,
                apellidop, apellidom, fk_rol, correo, extension, telefono, dir);
    }
}
