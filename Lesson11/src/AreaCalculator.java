public class AreaCalculator {
    public static double calculateAllFigure(Area[] figures){
        double total = 0;
        for(Area figure: figures){
            total+=figure.square();
        }
        return total;
    };
}
