package org.example;

public class UtilityFunctions {

    public static int calculateFactorial(int n){
        int result= 1;
        if(n<0){
            throw new IllegalArgumentException("Факториал не определён для отрицательных чисел");
        }else{
            for(int i = 1; i<=n;i++){
                result=result *i;
            }
        }
        return result;
    }
    public static double divide(double a, double b){
        if(b == 0){
            throw new IllegalArgumentException("деление на ноль запрещено");
        }
        return a/b;
    }
    public static double average(int[] numbers){
        if(numbers.length == 0){
            throw new IllegalArgumentException("Masssiv пустой");
        }
        int sum = 0;
        for (int i = 0; i<numbers.length; i++){
            sum += numbers[i];
        }
        return (double) sum/numbers.length;
    }
    public static double calculateSquareRoot(double x){
        if(x < 0){
            throw new IllegalArgumentException("Отрицательное число");
        }
        return Math.sqrt(x);
    }
    public static int sumOfDigits(int number){
        if(number <0 ){
            number = Math.abs(number);
        }
        int sum = 0;
        while (number > 0){
             int lastNum = number % 10;
             sum +=lastNum;
             number = number/10;
        }
        return sum;
    }
}
