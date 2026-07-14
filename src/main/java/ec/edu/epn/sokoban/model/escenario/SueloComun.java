package ec.edu.epn.sokoban.model.escenario;

public class SueloComun extends Suelo {
    public SueloComun(int f, int c) {
        super(f, c);
    }

    @Override
    public boolean esTransitable() {
        return true;
    }
}
