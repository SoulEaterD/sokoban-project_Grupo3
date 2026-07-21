package ec.edu.epn.sokoban.model.escenario;

import ec.edu.epn.sokoban.model.interfaces.Dibujador;

/**
 * Clase abstracta base para los componentes del escenario.
 */
public abstract class Casilla {
    private int fila;
    private int columna;
    private final GestorAcciones gestorAcciones;

    public Casilla() {
        this.gestorAcciones = new GestorAcciones();
    }

    public Casilla(int fila, int columna) {
        this.gestorAcciones = new GestorAcciones();
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public GestorAcciones getGestorAcciones() {
        return gestorAcciones;
    }

    /**
     * Delega el dibujo de la casilla al dibujador (Patrón Visitor).
     */
    public abstract <T> void dibujar(Dibujador<T> dibujador, T contenedor, int tamCelda);
}
