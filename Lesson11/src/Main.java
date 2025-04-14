public class Main {
    public static void main(String[] args) {
        Area [] figures = {
                new Square(9),
                new Tringle(5,8),
                new Circle(9),
        };
        System.out.println("Суммарная площадь: " + AreaCalculator.calculateAllFigure(figures));
    };
}