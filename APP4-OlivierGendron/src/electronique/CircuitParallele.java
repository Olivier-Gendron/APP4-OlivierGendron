package electronique;

import java.util.List;

public class CircuitParallele extends Circuit {

    public CircuitParallele(List<Composant> composants) {
        super(composants);
    }

    @Override
    public double calculerResistance() {
        double resistanceEquivalente = 0;
        for (Composant c : composants){
           resistanceEquivalente += 1/c.calculerResistance();
        }

        return 1/resistanceEquivalente;
    }
}
