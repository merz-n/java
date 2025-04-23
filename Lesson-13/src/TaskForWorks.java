import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

public class TaskForWorks {

    public static int countOccurance(List<String> list, String word) {
        int count = 0;
        for (String item : list){
            if (item.equals(word)) {
                count ++;
            }
        }
        return count;
    }
    public static List<Integer> toList(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
    }
    public static  List<Integer> findUnique(List<Integer> list1) {
        Set<Integer> set =  new LinkedHashSet<>(list1);
        return new ArrayList<>(set);
    }
    public static void calcOccurance(List<String> animals) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : animals) {
            int count = wordCount.getOrDefault(word, 0);
            wordCount.put(word, count + 1);
        }
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static List<WordStat> findOccurance(List<String> list2){
        Map<String, Integer> map = new HashMap<>();
        for (String word : list2) {
            int count = map.getOrDefault(word, 0);
            map.put(word, count + 1);
        }
        List<WordStat> newMap = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            newMap.add(new WordStat(entry.getKey(), entry.getValue()));
        }
        return newMap;
    }
}
