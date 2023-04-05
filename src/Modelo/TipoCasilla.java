
package Modelo;

/**
 *
 * @author Miguel
 */
public enum TipoCasilla {
    CASA_ROJO ("CASA_ROJO"), CASA_AMARILLO ("CASA_AMARILLO"), CASA_VERDE ("CASA_VERDE"),
    CASA_AZUL ("CASA_AZUL"), SEGURO_ROJO ("SEGURO_ROJO"), SEGURO_AMARILLO ("SEGURO_AMARILLO"),
    SEGURO_VERDE ("SEGURO_VERDE"), SEGURO_AZUL ("SEGURO_AZUL"), SEGURO ("SEGURO"),
    META_ROJO ("META_ROJO"), META__AMARILLO ("META_AMARILLO"), META_VERDE ("META_VERDE"),
    META_AZUL ("META_AZUL"), CASILLA ("CASILLA"), META ("META");
    private final String tipo;
    
    TipoCasilla(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
}
