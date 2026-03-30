package electronique;

import java.util.List;

public class CircuitSerie extends Circuit {

    public CircuitSerie(List<Composant> composants) {
        super(composants);

    }

    @Override
    public double calculerResistance() {
        double resistanceEquivalente = 0;
        for (Composant c : composants) {
            c.calculerResistance();
        }
        return resistanceEquivalente;
    }
}