import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*_________________ 1 _______________*/
        System.out.println("/*_________________ 1 _______________*/");
        List<Integer> result = Task.get10ErstTask();
        System.out.println("Task1 : \n" + result);
        /*_________________ 2 _______________*/
        System.out.println("/*_________________ 2 _______________*/");
        Random random = new Random();
        List<Integer> testInt = random.ints(10,1,51)
                .boxed()
                .collect(Collectors.toList());
        List<String> testWords = List.of("Java", "Stream", "API", "hi", "lambda", "ok", "filter");
        System.out.println("Data for the test: " + testInt);
        System.out.println("Data for the test: " + testWords);
        Predicate<Integer> predInt = n -> n%2==0;
        Predicate<String> predStr = s -> s.length() > 4;
        List<Integer> testEin = Task.taskTwo(testInt,predInt);
        List<String> testTwo = Task.taskTwo(testWords,predStr);
        System.out.println("Result n%2==0 : " + testEin);
        System.out.println("Result s.length() > 4: " + testTwo);
        /*_________________ 3 _______________*/
        System.out.println("/*_________________ 3 _______________*/");

        List<String> threeTest = List.of("Java", "Stream", "API", "hi", "lambda", "ok", "filter");
        System.out.println("Worters : " + threeTest);
        Predicate<String> predicate = s -> s.length()>1;
        String result3 = Task.taskThree(threeTest,predicate);
        System.out.println("Result : " + result3);
        /*_________________ 4 _______________*/

        System.out.println("/*_________________ 4 _______________*/");
        List<Integer> testInteger = random.ints(10,1,21)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Data for the test " + testInteger);
        List<Integer> asc = Task.taskFour(testInteger,SortDirection.ASC);
        List<Integer> desc = Task.taskFour(testInteger,SortDirection.DESC);
        System.out.println("Result ASC " + asc);
        System.out.println("Result DESC " + desc);

        /*_________________ 5 _______________*/

        System.out.println("/*_________________ 5 _______________*/");
        int n = 0;
        int n2 = 10;

        System.out.println("0! " + Task.taskFive(n));
        System.out.println("10! " + Task.taskFive(n2));

        /*_________________ 6 _______________*/
        System.out.println("/*_________________ 6 _______________*/");

        List<Boyscout> scouts = List.of(
                new Boyscout("Lena", 14, Team.RED),
                new Boyscout("Max", 12, Team.YELLOW),
                new Boyscout("Anna", 15, Team.RED),
                new Boyscout("Tom", 13, Team.YELLOW),
                new Boyscout("Ben", 16, Team.GREEN),
                new Boyscout("Sara", 11, Team.GREEN)
        );
        Camp camp = new Camp(scouts);
        Map<Team,List<Boyscout>> group = camp.split();
        System.out.println("Sort from oldest to youngest: ");

        for (Map.Entry<Team,List<Boyscout>> entry : group.entrySet()){
            System.out.println("Team " + entry.getKey() + ": ");
            for(Boyscout b : entry.getValue()){
                System.out.println("Name - " + b.getName()+ ", Age - " + b.getAge());
            }
        }
    }
}