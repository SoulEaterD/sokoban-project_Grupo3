package ec.edu.epn.sokoban.model.reglas;

/**
 * Notificador general de derrota de la partida (Patrón Observer simplificado).
 *
 * Cualquier {@code Accion} de cualquier grupo puede invocar
 * {@link #notificarDerrota()} cuando su mecánica implique que el jugador
 * pierde la partida (ej. lava, trampas, cajas destruidas, etc.).
 *
 * El controlador ({@code GestorVentanas}) registra mediante
 * {@link #registrar(Runnable)} la reacción ante la derrota (reiniciar el
 * nivel y refrescar la interfaz), manteniendo al modelo completamente
 * desacoplado de la vista y de JavaFX.
 */
public final class NotificadorDerrota {

    private static Runnable accionDerrota;

    private NotificadorDerrota() {
        // Clase de utilidad: no instanciable.
    }

    /**
     * Registra la reacción que será ejecutada cuando se notifique una derrota.
     *
     * @param accion lógica a ejecutar al perder la partida
     */
    public static void registrar(Runnable accion) {
        accionDerrota = accion;
    }

    /**
     * Notifica que la partida fue perdida, ejecutando la reacción registrada.
     * Si ninguna reacción fue registrada, la notificación es ignorada de forma segura.
     */
    public static void notificarDerrota() {
        if (accionDerrota != null) {
            accionDerrota.run();
        }
    }
}
