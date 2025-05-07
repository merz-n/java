import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class MethodsArrays {
    public static <T> void  swapPairs(T[] array){
        for (int i=0;i<array.length - 1; i+=2){
                T temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
        }

    }
    public static <T> List<T> listArray(T[] array){
        List<T> list = new ArrayList<>(Arrays.asList(array));
        return list;
    }

}
