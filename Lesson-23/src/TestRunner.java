import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunner {
    public static void start(Class<?> clazz)throws Exception{
        Method[] methods = clazz.getDeclaredMethods();
        Method beforMethod = null;
        Method afterMethod = null;
        List<Method> testMetod = new ArrayList<>();
        Map<Method,Integer> testOrderMap = new HashMap<>();

        for(Method method : methods){
            if(method.isAnnotationPresent(BeforeSuite.class)){
                if(beforMethod != null){
                    throw new RuntimeException("Exeption, can only one whis anotation @BeforeSuite ");
                }
                beforMethod = method;
            }
            if(method.isAnnotationPresent(AfterSuite.class)){
                if(afterMethod!=null){
                    throw new RuntimeException("Exeption, can only one whis anotation @AfterSuite ");
                }
                afterMethod = method;
            }
            if(method.isAnnotationPresent(Test.class)){
                Test anotation = method.getAnnotation(Test.class);
                int order = anotation.order();
                testMetod.add(method);
                testOrderMap.put(method,order);

            }
        }
        Object instance = clazz.getDeclaredConstructor().newInstance();
        if (beforMethod != null){
            beforMethod.setAccessible(true);
            beforMethod.invoke(instance);
        }
        testMetod.sort((m1,m2) -> Integer.compare(testOrderMap.get(m1),testOrderMap.get(m2) ));
        for(Method method : testMetod){
            method.setAccessible(true);
            method.invoke(instance);
        }
        if(afterMethod != null){
            afterMethod.setAccessible(true);
            afterMethod.invoke(instance);
        }
    }

}
