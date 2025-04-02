import java.util.*;
import java.util.Random;

public class lesson6neu {
    public static void main(String[] args) {
        ///////1///////
        System.out.println("First task:");
        int[] source = {1, 2, 3, 4};
        int[] target = {};
        // Result: [1, 2, 3, 4]

        /*  int[] source = {1,2,3,4};
        int[] target = {5, 6, 7};
        //Result: [5, 6, 7, 1, 2, 3, 4]*/
        System.out.println(Arrays.toString(source));
        System.out.println(Arrays.toString(target));

        target = arrayCopy(source, target);
        System.out.println(Arrays.toString(target));
        //////2//////
        System.out.println("Second task(Scheikernaja sortirovka):");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Imput arraysize:");
        int arraysize = scanner.nextInt();
        int[] array = new int[arraysize];
        Random newRandom = new Random();

        for(int i=0;i<array.length;i++ ){
            array[i] = newRandom.nextInt(arraysize) + 1;
            System.out.print(array[i] +" ");
        }
        System.out.println();
        shakerSort(array);
        System.out.println("Sorted array:");
        for(int i :array){
            System.out.print(i +" ");
        }


    }
//////1///////
    public static int[] arrayCopy(int[] source, int[] target){
        int startIdx = target.length;
        int[] newTarget = Arrays.copyOf(target, target.length + source.length);
        for (int i = 0; i < source.length; i++){
            newTarget[startIdx + i] = source[i];
        }
        return newTarget;
    }
public static void shakerSort(int[]array) {
    int leftSide = 0;
    int rightSide = array.length - 1;
    int temp;
    boolean swap;
    do {
        swap=false;
        for (int i = leftSide; i < rightSide; i++) {
            if (array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swap=true;
            }
        }
        rightSide--;
        for (int i = rightSide; i > leftSide; i--) {
            if (array[i] < array[i - 1]) {
                temp = array[i];
                array[i] = array[i - 1];
                array[i-1]=temp;
                swap=true;
            }

        }
        leftSide++;
    }
    while (swap);

}
}
