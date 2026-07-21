package ec.edu.epn.sokoban.model.escenario;

import ec.edu.epn.sokoban.model.interfaces.Accion;
import ec.edu.epn.sokoban.model.reglas.NotificadorDerrota;

/**
 * Acción concreta del Grupo 2: terreno peligroso de Lava.
 *
 * Regla de la mecánica: cualquier entidad (Personaje o Caja) que ingrese a
 * una casilla con lava provoca la derrota inmediata de la partida.
 * - Si el personaje la pisa, se quema y se pierde.
 * - Si una caja cae en ella, la partida también se pierde.
 *
 * La derrota se comunica mediante {@link NotificadorDerrota}, sin acoplar
 * esta acción al controlador ni a la vista. No requiere distinguir el tipo
 * de entidad (sin {@code instanceof}), ya que el resultado es el mismo
 * para ambas.
 */
public class Lava implements Accion {

    /**
     * Se ejecuta después de que la entidad fue movida con éxito a la casilla:
     * la partida se declara perdida.
     */
    @Override
    public void iniciarAccion(Casilla casillaActual, Tablero tablero, Casilla entidad) {
        NotificadorDerrota.notificarDerrota();
    }

    /**
     * La lava permite el ingreso de cualquier entidad (justamente para que
     * caiga en ella y se pierda la partida).
     */
    @Override
    public boolean puedeIngresarEntidad(Tablero tablero, Casilla entidad) {
        return true;
    }

    /**
     * Identificador del sprite visual que dibuja el PanelTablero.
     */
    @Override
    public String getSpriteKey() {
        return "LAVA";
    }
}
