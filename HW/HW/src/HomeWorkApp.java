import java.util.Scanner;

public class HomeWorkApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("1: ");
        printThreeWords();
        System.out.print("2: ");
        checkSumSign();
        System.out.print("3: ");
        printColor();
        System.out.print("4: ");
        compareNumbers();
        System.out.print("5: ");
        boolean sumInRange = number(scanner);
        System.out.println("Summa in diaposon 10-20? " + sumInRange);
        System.out.print("6: ");
        positivNumber(scanner);
        System.out.print("7: ");
        boolean numberNP = numberNegativOderPositiv(scanner);
        System.out.println("Result: " + numberNP);
        System.out.print("8: ");
        malTrhee(scanner);
        System.out.print("9: ");
        boolean isVis = VisokosniyYear(scanner);
        System.out.println("Is visokosniy year: " + isVis);

        scanner.close();
    }

    //Первое задание
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    //Второе задание
    public static void checkSumSign() {
        int a = -40;
        int b = 20;
        int c = a + b;

        if(c >= 0) {
            System.out.println("The sum is positive = " + c);
        }
        else System.out.println("The sum is negativ = "+ c);
    }
    //Третье задание
    public static void printColor()  {
        int value = -40;
        if(value <= 0) {
            System.out.println("Red");
        }
        else if (value > 0 && value <= 100) {
            System.out.println("Yellow");
        }
        else System.out.println("Green");
    }
    //Четвертое задание
    public static void compareNumbers()  {
        int a = -40;
        int b = 40;
        if(a >= b){
            System.out.println("a >= b");
        }else{System.out.println("a < b");
        }
    }
    //Пятое задание
    public static boolean number(Scanner scanner) {
        System.out.print("Input first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Input second number: ");
        int num2 = scanner.nextInt();
        int sum = num1 + num2;
        return sum >= 10 && sum <= 20;
    }
    //Шестое задание
    public static void positivNumber(Scanner scanner)  {

        System.out.println(" Input number: ");
        int num = scanner.nextInt();
        if (num >= 0){System.out.println("Positiv");
        }else System.out.println("Negativ");
    }
    //Седьмое задание
    public static boolean numberNegativOderPositiv(Scanner scanner) {
        System.out.print("Input number: ");
        int num = scanner.nextInt();
        return isNegative(num);
    }

    public static boolean isNegative(int num) {
        return num < 0;
    }
    //Восьмое задание
    public static void malTrhee(Scanner scanner) {
        System.out.print("Input number: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input line: ");
        String line = scanner.nextLine();

        for (int i = 0; i < num; i++) {
            System.out.println(line);
        }

    }
    //Девятое задание
    public static boolean VisokosniyYear(Scanner scanner) {
        System.out.print("Input year: ");
        int year = scanner.nextInt();
        return isVisokosniYear(year);
    }

    public static boolean isVisokosniYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}


