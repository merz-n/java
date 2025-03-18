import java.util.Scanner;
import java.util.Random;


public class Lesson4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Первое задание
        System.out.println("Task1: ");
        int[] arr = {0, 1, 1, 1, 0, 1, 0, 1};
        System.out.println("Massiv: ");
        printArray(arr);
        System.out.println("New massiv: ");
        taskFirst(arr);
        System.out.println(" ");
        //Второе задание
        System.out.println("Task2: ");
        taskSecond();
        //Третье задание
        System.out.println("Task3: ");
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(arr3);
        taskThird(arr3);
        printArray(arr3);
        //Четвертое задание
        System.out.println("Task4: ");
        int[][] arr4 = taskFourth(scanner);
        printArr2(arr4);

        //Пятое задание
        System.out.println("Task5: ");
        taskFifth(scanner);

        //Шестое задание
        System.out.println("Task6: ");
        taskSixth(scanner);

        scanner.close();
    }

    //Первое задание
    public static void taskFirst(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0){arr[i] =1;} else {arr[i] = 0;}
            System.out.print(arr[i] + " ");
        }

    }
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
            System.out.println();
    }

    //Второе задание
    public static void taskSecond() {
        int[] arr1 = new int[100];
        for(int i = 0; i < 100; i++) {
                arr1[i] = i+1;

                System.out.println("arr[" + i + "] = " + arr1[i]);
            }
        }
    //Третье задание
    public static void taskThird(int[] arr3) {

        for(int i = 0; i < arr3.length; i++) {
            if (arr3[i]<6){
                arr3[i] *= 2;
            }

        }
    }
    //Четвертое задание

     public static int[][] taskFourth(Scanner scanner) {
        System.out.print("Enter the length: ");
        int k = scanner.nextInt();
        int[][] arr4 = new int[k][k];
        for (int i = 0; i < k; i++) {
            arr4[i][i] = 1;
            arr4[i][k - 1 - i] = 1;
        }
        return arr4;
    }

    public static void printArr2(int[][] arr4) {
            for (int i = 0; i < arr4.length; i++) {
                for (int j = 0; j < arr4[i].length; j++) {
                    System.out.print(arr4[i][j] + " ");
                }
                System.out.println();
            }


    }

    //Пятое задание
    public static void taskFifth(Scanner scanner) {
        System.out.print("Enter the length of the array: ");
        int len = scanner.nextInt();
        System.out.print("Enter value: ");
        int initialValue = scanner.nextInt();
        int[] mas = new int[len];
        for (int i = 0; i < len; i++) {
            mas[i] = initialValue;
        }
        for (int num : mas) {
            System.out.print(num + " ");
        }
        System.out.println();

    }
    //Шестое задание
    public static void taskSixth(Scanner scanner) {
        System.out.print("Enter the length of the array: ");
        int k = scanner.nextInt();

        Random random = new Random();
        int[] array6 = new int[k];

        for (int i = 0; i < k; i++) {
            array6[i] = random.nextInt(100);
        }
        for (int n : array6) {
            System.out.print(n + " ");
        }
        System.out.println();

        int min = array6[0];
        int max = array6[0];
        for (int n : array6) {
            if (n < min) {
                min = n;
            }
            if (n > max) {
                max = n;
            }

        }
        System.out.println("Max: " + max);
        System.out.println("Max: " + min);
    }
}
