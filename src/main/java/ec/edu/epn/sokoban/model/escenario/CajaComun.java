package ec.edu.epn.sokoban.model.escenario;

import ec.edu.epn.sokoban.Direccion;

/**
 * Representa una caja común y corriente.
 */
public class CajaComun extends Caja {

    public CajaComun(int fila, int columna) {
        super(fila, columna);
    }

    public CajaComun(int fila, int columna, boolean enMeta) {
        super(fila, columna, enMeta);
    }

    @Override
    public boolean mover(Direccion d, Tablero t) {
        if (d == null || t == null) {
            return false;
        }

        int filaOrigen = getFila();
        int columnaOrigen = getColumna();
        int filaDestino = filaOrigen + d.getDeltaFila();
        int columnaDestino = columnaOrigen + d.getDeltaColumna();

        Casilla destino = t.obtenerCasilla(filaDestino, columnaDestino);
        boolean posteriorEsMeta = destino instanceof Meta;

        // Cambiar estado de enMeta en la caja
        setEnMeta(posteriorEsMeta);

        // Mutar coordenadas internas
        setFila(filaDestino);
        setColumna(columnaDestino);

        t.actualizarCasilla(filaDestino, columnaDestino, this);
        return true;
    }
}
