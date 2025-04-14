public class Tringle implements Area {
private double h, base;

    public Tringle(double h, double base) {
        this.h = h;
        this.base = base;
    }

    public double square(){
        return (h*base)/2;
    }
}
