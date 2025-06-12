import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTest {

    private ArrayProcess process;
    @BeforeAll
    static void initAll() {
        System.out.println("==> Begin test");
    }
    @BeforeEach
    void init() {
        process = new ArrayProcess();
        System.out.println("--> Preparing for the test");
    }

    @Test
    public void testNormalCase(){
        int [] arr = {1, 2, 4, 5, 6};
        int [] result = {5, 6};
        assertArrayEquals(result, process.getElementsAfterLastFour(arr));
    }
    @Test
    public void testFourAtStart(){
        int [] arr = {4, 2, 1, 5, 6};
        int [] result = { 2, 1, 5, 6};
        assertArrayEquals(result, process.getElementsAfterLastFour(arr));
    }
    @Test
    public void testFourAtEnd(){
        int [] arr = { 2, 1, 5, 6, 4};
        int [] result = {};
        assertArrayEquals(result, process.getElementsAfterLastFour(arr));
    }
    @Test
    public void testNoFour(){
        int [] arr = { 2, 1, 5, 6};
        assertThrows(RuntimeException.class, ()->process.getElementsAfterLastFour(arr));
    }

    @Test
    public void testOnlyOne(){
        int [] arr = { 1, 1, 1, 1};
        assertFalse(process.containsOnlyOneAndFour(arr));
    }
    @Test
    public void testOnlyFour(){
        int [] arr = { 4, 4, 4, 4};
        assertFalse(process.containsOnlyOneAndFour(arr));
    }
    @Test
    public void testOneandFour(){
        int [] arr = { 1, 4, 1, 4};
        assertTrue(process.containsOnlyOneAndFour(arr));
    }
    @Test
    public void testOtherNumer(){
        int [] arr = { 1, 4, 1, 4, 5};
        assertFalse(process.containsOnlyOneAndFour(arr));
    }
    @Test
    public void testEmptyArray(){
        int [] arr = {};
        assertFalse(process.containsOnlyOneAndFour(arr));
    }
    @AfterEach
    void tearDown() {
        System.out.println("<-- End Test");
    }
    @AfterAll
    static void tearDownAll() {
        System.out.println("==> End All Tests");
    }

}

