package model;

import java.io.Serializable;

public class MetodoPago implements Serializable {

    private long metodoId;
    private String metodoPago;

    public MetodoPago() {

    }

    public MetodoPago(long metodoId, String metodoPago) {
        setMetodoId(metodoId);
        setMetodoPago(metodoPago);
    }

    public long getMetodoId() {
        return metodoId;
    }

    public void setMetodoId(long metodoId) {
        this.metodoId = metodoId;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = (metodoPago != null) ? metodoPago : "";
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nMetodo de Pago: %s\n", metodoId, metodoPago);
    }
}
