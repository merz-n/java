public class Square implements Area{
    public Square(double sideLenght) {
        this.sideLenght = sideLenght;
    }

    private double sideLenght;
    public double square(){
        return sideLenght*sideLenght;
    }
}
