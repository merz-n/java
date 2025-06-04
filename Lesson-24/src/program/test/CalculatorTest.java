package program.test;

import framework.annotation.Test;
import framework.assertions.Assertions;
import program.Calculator;

import java.util.List;
import java.util.Set;

public class CalculatorTest {
    @Test
    public void testSumOfPositives() {
        Assertions.equals(5, Calculator.sum(3, 2));
    }

    @Test
    public void testSumWithZero() {
        Assertions.equals(4, Calculator.sum(4, 0));
    }

    @Test
    public void testSumOfNegatives() {
        Assertions.equals(-9, Calculator.sum(-4, -5));
    }

    @Test
    public void testSumOfPositiveAndNegative() {
        Assertions.equals(-1, Calculator.sum(4, -5));
    }

    @Test
    public void testSumOfPositiveAndNegativeWrong() {
        Assertions.equals(-1, Calculator.sum(3, -5));
    }

    @Test
    public void testSequenceOfOperation() {
        var result = Calculator.sum(2, 5);
        System.out.println("Result: " + result);
    }

    @Test
    public void testStringContains() {
        String value = "some other value";

        Assertions.contains(value, "other");
    }

    @Test
    public void testStringContainsWrong() {
        String value = "some other value";

        Assertions.contains(value, "others");
    }
    @Test
    public void testRecursiveArrayEquals() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};

        Assertions.equalRecursively(a, b);
    }

    @Test
    public void testRecursiveArrayNotEqual() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 4};

        Assertions.equalRecursively(a, b);
    }

    @Test
    public void testRecursiveListEquals() {
        List<String> a = List.of("a", "b");
        List<String> b = List.of("a", "b");

        Assertions.equalRecursively(a, b);
    }

    @Test
    public void testRecursiveSetNotEqual() {
        Set<Integer> a = Set.of(1, 2, 3);
        Set<Integer> b = Set.of(1, 2);

        Assertions.equalRecursively(a, b);
    }

    @Test
    public void testRecursiveNulls() {
        Assertions.equalRecursively(null, null);
    }
    @Test
    public void testArrayContainsSubarrayFailure(){
        Integer[] array = {5, 9, 1, 2, 4, 10};
        Integer[] subarray = {1, 2, 3};

        Assertions.contain(array,subarray);
    }
    @Test
    public void testArrayContainsSubarraySuccess() {
        Integer[] array1 = {5, 9, 1, 2, 3, 10};
        Integer[] subarray1 = {1, 2, 3};

        Assertions.contain(array1, subarray1);
    }

}
