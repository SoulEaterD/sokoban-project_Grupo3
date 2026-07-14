package ec.edu.epn.sokoban.model.reglas;

import ec.edu.epn.sokoban.model.escenario.Caja;
import ec.edu.epn.sokoban.model.escenario.Casilla;
import ec.edu.epn.sokoban.model.escenario.Tablero;
import ec.edu.epn.sokoban.model.historial.Nivel;
import ec.edu.epn.sokoban.model.persistencia.GestorPersistencia;

import java.util.List;

/**
 * Clase encargada de definir las reglas de negocio generales del juego,
 * incluyendo la cadena de responsabilidad para las colisiones y la verificación de victoria.
 */
public class ReglasJuego {
    private final ManejadorColision cadenaColisiones;

    public ReglasJuego() {
        ManejadorColision manejadorPared = new ManejadorPared();
        ManejadorColision manejadorCaja = new ManejadorCaja();
        ManejadorColision manejadorMovBase = new ManejadorMovimientoBase();

        manejadorPared.setSiguiente(manejadorCaja);
        manejadorCaja.setSiguiente(manejadorMovBase);
        this.cadenaColisiones = manejadorPared;
    }

    public ManejadorColision getCadenaColisiones() {
        return cadenaColisiones;
    }

    /**
     * Verifica si se han cumplido las condiciones de victoria para el nivel actual
     * y, de ser así, guarda el progreso del juego.
     *
     * @param nivelActual        el nivel que se está jugando
     * @param tableroActual      el tablero con el estado actual
     * @param nivelesDisponibles la lista de todos los niveles cargados
     * @param persistencia       el gestor de persistencia del progreso
     */
    public void verificarYRegistrarVictoria(
            Nivel nivelActual, 
            Tablero tableroActual, 
            List<Nivel> nivelesDisponibles, 
            GestorPersistencia persistencia) {
        if (nivelActual == null || tableroActual == null) {
            return;
        }

        boolean todasMetasSatisfechas = true;
        boolean algunaMetaExiste = false;

        for (int f = 0; f < tableroActual.getFilas(); f++) {
            for (int c = 0; c < tableroActual.getColumnas(); c++) {
                if (tableroActual.esMeta(f, c)) {
                    algunaMetaExiste = true;
                    Casilla casilla = tableroActual.obtenerCasilla(f, c);
                    if (!(casilla instanceof Caja)) {
                        todasMetasSatisfechas = false;
                    }
                }
            }
        }

        if (algunaMetaExiste && todasMetasSatisfechas) {
            nivelActual.marcarComoCompletado();
            if (persistencia != null) {
                persistencia.guardarProgreso(nivelesDisponibles);
            }
        }
    }
}
