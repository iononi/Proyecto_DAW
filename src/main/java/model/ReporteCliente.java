package model;

import java.io.Serializable;

public class ReporteCliente implements Serializable {

    public int folio;
    private int fkCliente;
    private int fkTipoResiduo;
    private int fkMetodoPago;
    private boolean pagado;
    private short fkEstado;


    public ReporteCliente() {

    }

    public ReporteCliente(int folio, int fkCliente, int fkTipoResiduo, int fkMetodoPago, boolean pagado, short fkEstado) {
        setFolio(folio);
        setFkCliente(fkCliente);
        setFkTipoResiduo(fkTipoResiduo);
        setFkMetodoPago(fkMetodoPago);
        setPagado(pagado);
        setFkEstado(fkEstado);
    }

    // Insertion constructor
    public ReporteCliente(int fkCliente, int fkTipoResiduo, int fkMetodoPago, boolean pagado, short fkEstado) {
        setFkCliente(fkCliente);
        setFkTipoResiduo(fkTipoResiduo);
        setFkMetodoPago(fkMetodoPago);
        setPagado(pagado);
        setFkEstado(fkEstado);
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = (folio < 0) ? 0 : folio;
    }

    public int getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(int fkCliente) {
        this.fkCliente = (fkCliente <= 0) ? 1 : fkCliente;
    }

    public int getFkTipoResiduo() {
        return fkTipoResiduo;
    }

    public void setFkTipoResiduo(int fkTipoResiduo) {
        this.fkTipoResiduo = (fkTipoResiduo <= 0) ? 1 : fkTipoResiduo;
    }

    public int getFkMetodoPago() {
        return fkMetodoPago;
    }

    public void setFkMetodoPago(int fkMetodoPago) {
        this.fkMetodoPago = (fkMetodoPago <= 0) ? 1 : fkMetodoPago;
    }

    public boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public short getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(short fkEstado) {
        this.fkEstado = fkEstado;
    }

    @Override
    public String toString() {
        return String.format("Folio: %d\nUsuario: %d\nTipo Residuo: %d\nMetodo de pago: %d\nPagado: %b\nEstado: %d",
                folio, fkCliente, fkTipoResiduo, fkMetodoPago, pagado, fkEstado);
    }
}
