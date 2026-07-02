package ec.edu.epn.sokoban.model.historial;

import java.util.Stack;

public class HistorialMovimientos {
    private int movimientosContador;
    private Stack<PartidaMomento> historial;

    public HistorialMovimientos() {
        this.historial = new Stack<>();
        this.movimientosContador = 0;
    }

    public int getMovimientosContador() {
        return movimientosContador;
    }

    public void setMovimientosContador(int movimientosContador) {
        this.movimientosContador = movimientosContador;
    }

    public void registrarEstado(PartidaMomento e) {
        historial.push(e);
        movimientosContador++;
    }

    public PartidaMomento extraerUltimoEstado() {
        if (!historial.isEmpty()) {
            return historial.pop();
        }
        return null;
    }

    public void vaciarHistorial() {
        historial.clear();
        movimientosContador = 0;
    }
}
