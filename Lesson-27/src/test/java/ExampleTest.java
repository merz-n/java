import org.example.UtilityFunctions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    @BeforeAll
    static void initAll() {
        System.out.println("==> Begin test");
    }
    @BeforeEach
    void init() {
        System.out.println("--> Preparing for the test");
    }

    @Test
    public void testcalculateFactorial(){
        assertEquals(120, UtilityFunctions.calculateFactorial(5));
    }
    @Test
    public void testcalculateFactorialOfZwero(){
        assertEquals(1, UtilityFunctions.calculateFactorial(0));
    }
    @Test
    public void testcalculateFactorialThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> {
            UtilityFunctions.calculateFactorial(-3);
        });
    }

    @Test
    public void divide(){
        assertEquals(5.0, UtilityFunctions.divide(10,2));
    }
    @Test
    public void divideMitDev(){
        assertEquals(3.5, UtilityFunctions.divide(7,2));
    }
    @Test
    public void divideThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> {
            UtilityFunctions.divide(5,0);
        });
    }

    @Test
    public void average(){
        int[] mas = {1,2,3,4,5};
        assertEquals(3.0, UtilityFunctions.average(mas));
    }
    @Test
    public void averageOne(){
        int[] mas = {7};
        assertEquals(7.0, UtilityFunctions.average(mas));
    }
    @Test
    public void averageThrowsException(){
        int[] mas = {};
        Assertions.assertThrows(IllegalArgumentException.class,()-> {
            UtilityFunctions.average(mas);
        });
    }

    @Test
    public void calculateSquareRoot(){
        assertEquals(3.0, UtilityFunctions.calculateSquareRoot(9));
    }
    @Test
    public void calculateSquareRootOne(){

        assertEquals(0.0, UtilityFunctions.calculateSquareRoot(0));
    }
    @Test
    public void calculateSquareRootThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> {
            UtilityFunctions.calculateSquareRoot(-4);
        });
    }

    @Test
    public void sumOfDigits(){
        assertEquals(6, UtilityFunctions.sumOfDigits(123));
    }
    @Test
    public void sumOfDigitsOne(){

        assertEquals(6, UtilityFunctions.sumOfDigits(-321));
    }
    @Test
    public void sumOfDigitsNull(){
        assertEquals(0, UtilityFunctions.sumOfDigits(0));
    }

   /* @Test
    public void brokenTest(){
        assertEquals(0, UtilityFunctions.calculateFactorial(5));
    }*/

    @AfterEach
    void tearDown() {
        System.out.println("<-- End Test");
    }
    @AfterAll
    static void tearDownAll() {
        System.out.println("==> End All Tests");
    }
}
