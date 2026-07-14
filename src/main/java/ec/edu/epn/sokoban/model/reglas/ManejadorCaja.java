package ec.edu.epn.sokoban.model.reglas;

import ec.edu.epn.sokoban.Direccion;
import ec.edu.epn.sokoban.model.escenario.Caja;
import ec.edu.epn.sokoban.model.escenario.Casilla;
import ec.edu.epn.sokoban.model.escenario.Personaje;
import ec.edu.epn.sokoban.model.escenario.SueloComun;
import ec.edu.epn.sokoban.model.escenario.Tablero;

/**
 * Manejador concreto que evalúa la física de empuje interactivo de cajas.
 * Valida si la casilla posterior es transitable y realiza el traslado de estado físico de la caja y el personaje.
 */
public class ManejadorCaja implements ManejadorColision {
    private ManejadorColision siguiente;

    @Override
    public void setSiguiente(ManejadorColision siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public boolean procesarMovimiento(Tablero tablero, Casilla origen, Direccion dir) {
        if (tablero == null || origen == null || dir == null) {
            return false;
        }

        int filaDestino = origen.getFila() + dir.getDeltaFila();
        int columnaDestino = origen.getColumna() + dir.getDeltaColumna();

        Casilla destino = tablero.obtenerCasilla(filaDestino, columnaDestino);
        if (destino != null && destino.esEmpujable()) {
            Caja caja = (Caja) destino;
            // Si la caja ya está sobre una meta, no se puede volver a mover
            if (tablero.esMeta(filaDestino, columnaDestino)) {
                return false;
            }

            int filaDestinoCaja = filaDestino + dir.getDeltaFila();
            int columnaDestinoCaja = columnaDestino + dir.getDeltaColumna();

            // Validar límites del tablero tras la caja
            if (filaDestinoCaja < 0 || filaDestinoCaja >= tablero.getFilas() ||
                columnaDestinoCaja < 0 || columnaDestinoCaja >= tablero.getColumnas()) {
                return false;
            }

            Casilla casillaDestinoCaja = tablero.obtenerCasilla(filaDestinoCaja, columnaDestinoCaja);
            
            // Validar que la casilla tras la caja sea transitable
            if (casillaDestinoCaja == null || !casillaDestinoCaja.esTransitable()) {
                return false;
            }

            // Mover la caja a su nueva posición usando su comportamiento autónomo
            caja.mover(dir, tablero);

            // Liberar la casilla de donde vino la caja (siempre será SueloComun,
            // porque si hubiese estado sobre una Meta el movimiento habría sido bloqueado al inicio)
            Casilla casillaLiberada = new SueloComun(filaDestino, columnaDestino);
            tablero.actualizarCasilla(filaDestino, columnaDestino, casillaLiberada);

            // Desplazar al personaje a la casilla liberada usando su comportamiento autónomo
            if (origen instanceof Personaje personaje) {
                personaje.mover(dir, tablero);
            }
            return true;
        }

        // Si no es una caja, delegar al siguiente manejador
        if (siguiente != null) {
            return siguiente.procesarMovimiento(tablero, origen, dir);
        }

        return false;
    }
}
