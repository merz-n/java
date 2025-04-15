public class Main {
    public static void main(String[] args) {
        ArrayValueCalculator calculator = new ArrayValueCalculator();
        try {
            int result = calculator.doCalc(calculator.correctArray);
            System.out.println("Summa: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            int result = calculator.doCalc(calculator.wrongDataArray);
            System.out.println("Summa: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            int result = calculator.doCalc(calculator.wrongSizeArray);
            System.out.println("Summa: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}