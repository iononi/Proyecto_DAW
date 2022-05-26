package model;

import java.io.Serializable;

public class ReporteCliente implements Serializable {

    public long folio;
    private long fkCliente;
    private long fkTipoResiduo;
    private long fkMetodoPago;
    private boolean pagado;
    private short fkEstado;


    public ReporteCliente() {

    }

    public ReporteCliente(long folio, long fkCliente, long fkTipoResiduo, long fkMetodoPago, boolean pagado, short fkEstado) {
        this.folio = folio;
        this.fkCliente = fkCliente;
        this.fkTipoResiduo = fkTipoResiduo;
        this.fkMetodoPago = fkMetodoPago;
        this.pagado = pagado;
        setFkEstado(fkEstado);
    }

    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = (folio < 0) ? 0 : folio;
    }

    public long getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(long fkCliente) {
        this.fkCliente = (fkCliente <= 0) ? 1 : fkCliente;
    }

    public long getFkTipoResiduo() {
        return fkTipoResiduo;
    }

    public void setFkTipoResiduo(long fkTipoResiduo) {
        this.fkTipoResiduo = (fkTipoResiduo <= 0) ? 1 : fkTipoResiduo;
    }

    public long getFkMetodoPago() {
        return fkMetodoPago;
    }

    public void setFkMetodoPago(long fkMetodoPago) {
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
