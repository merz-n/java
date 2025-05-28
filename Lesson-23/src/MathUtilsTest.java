public class MathUtilsTest {
    @BeforeSuite
    public void setUp(){
        System.out.println("Begin test");
    }

    @Test(order = 1)
    public void testAdition(){
        if(MathUtils.add(2,3)==5){
            System.out.println("testAdition passed");
        }else{
            System.out.println("testAdition failed");
        }
    }

    @Test(order = 2)
    public void testSubstract(){
        if(MathUtils.substract(6,3)==3){
            System.out.println("testSubstract passed");
        }else{
            System.out.println("testSubstract failed");
        }
    }


    @AfterSuite
    public void setOut(){
        System.out.println("Test completed");
    }
}
