package ec.edu.epn.sokoban.model.escenario;

/**
 * Representa una pared física común y corriente.
 */
public class ParedComun extends Pared {
    public ParedComun(int f, int c) {
        super(f, c);
    }

    @Override
    public boolean esTransitable() {
        return false;
    }
}
