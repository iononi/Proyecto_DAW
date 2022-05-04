package model;

import java.io.Serializable;

public class ReporteCliente implements Serializable {

    public long folio;
    private long fkCliente;
    private long fkTipoResiduo;
    private long fkMetodoPago;
    private boolean pagado;


    public ReporteCliente() {

    }

    public ReporteCliente(long folio, long fkCliente, long fkTipoResiduo, long fkMetodoPago, boolean pagado) {
        this.folio = folio;
        this.fkCliente = fkCliente;
        this.fkTipoResiduo = fkTipoResiduo;
        this.fkMetodoPago = fkMetodoPago;
        this.pagado = pagado;
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

    @Override
    public String toString() {
        return String.format("Folio: %d\nCliente: %d\nTipo Residuo: %d\nMetodo de pago: %d\nPagado: %b",
                folio, fkCliente, fkTipoResiduo, fkMetodoPago, pagado);
    }
}
