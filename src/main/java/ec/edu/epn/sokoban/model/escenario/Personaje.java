package ec.edu.epn.sokoban.model.escenario;

/**
 * El personaje del escenario es representado como una casilla no transitable.
 */
public class Personaje extends Casilla {
    private boolean enMeta;

    /**
     * Un personaje sin coordenadas explicitas es inicializado.
     */
    public Personaje() {
        this.enMeta = false;
    }

    /**
     * Un personaje es inicializado con coordenadas dentro del escenario.
     *
     * @param fila    fila asignada al personaje
     * @param columna columna asignada al personaje
     */
    public Personaje(int fila, int columna) {
        super(fila, columna);
        this.enMeta = false;
    }

    public boolean isEnMeta() {
        return enMeta;
    }

    public void setEnMeta(boolean enMeta) {
        this.enMeta = enMeta;
    }

    /**
     * El estado de transitabilidad es retornado como bloqueado.
     *
     * @return false porque el personaje bloquea el paso libre
     */
    @Override
    public boolean esTransitable() {
        return false;
    }
}
