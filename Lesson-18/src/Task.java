import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task {
    public static List<Integer> get10ErstTask(){

        Random random = new Random();
        List<Integer> list = random.ints(100, 1, 1001)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list);
        List<Integer> result = list.stream()
                .distinct()
                .sorted()
                // .peek(e-> System.out.println("After sorted(): " + e))
                .limit(10)
                //.peek(e-> System.out.println("After limit(10): " + e))
                .sorted(Comparator.reverseOrder())
                .peek(e-> System.out.println("After sorted(Comparator.reverseOrder()): " + e))
                .collect(Collectors.toList());
        return result;
    }
    public static <T> List<T> taskTwo(Collection <T> collection, Predicate<T> predicate){
       List<T> result2 = collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        return result2;
    }
    public static String taskThree(Collection<String> collection,Predicate<String> predicate){
     String result3 = collection.stream()
             .filter(predicate)
             .collect(Collectors.joining("|"));
     return result3;
    }
    public static List<Integer> taskFour(Collection<Integer> collection, SortDirection sortDirection){
        List<Integer> result4 = collection.stream()
                .distinct()
                .sorted( sortDirection  == SortDirection.ASC
                ? Comparator.naturalOrder()
                        :Comparator.reverseOrder())
                .collect(Collectors.toList());
        return result4;
    }
    public static Integer taskFive(int n){
        if (n <0){
            throw new IllegalArgumentException("! muss n>0");
        }
        if (n==0){
            return 1;
        }
        int resultFive = IntStream.rangeClosed(1,n)
                .reduce(1,(a,b)->a*b);
        return resultFive;
    }
}
