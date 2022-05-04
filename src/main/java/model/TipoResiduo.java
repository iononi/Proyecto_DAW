package model;

import java.io.Serializable;

public class TipoResiduo implements Serializable {

    private int residuoId;
    private String tipoResiduo;

    public TipoResiduo() {

    }

    public TipoResiduo(int residuoId, String tipoResiduo) {
        setResiduoId(residuoId);
        setTipoResiduo(tipoResiduo);
    }

    public int getResiduoId() {
        return residuoId;
    }

    public void setResiduoId(int residuoId) {
        this.residuoId = residuoId;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = (tipoResiduo != null) ? tipoResiduo : "";
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nTipo Residuo: %s", residuoId, tipoResiduo);
    }
}
