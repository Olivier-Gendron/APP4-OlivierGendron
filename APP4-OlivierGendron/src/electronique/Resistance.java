package electronique;

public class Resistance extends Composant {
    private double valeurResistance;

    public Resistance(double valeurResistance) {
        setValeurResistance(valeurResistance);
    }

    @Override
    public double calculerResistance() {
        return getValeurResistance();
    }

    public double getValeurResistance() {
        return valeurResistance;
    }

    public void setValeurResistance(double valeurResistance) {
        this.valeurResistance = valeurResistance;
    }

}
