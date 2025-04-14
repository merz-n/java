public class Circle implements Area {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double square(){
        return Constant.PI*radius*radius;
    }
}
