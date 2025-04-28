import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import phonebook.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("______________________________  1  _________________________________");
        System.out.println("1. countOccurance");
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "apple", "kiwi",
                "banana", "grape", "apple", "melon", "kiwi"));
        System.out.println(list);
        String wordToFind = "melon"; // то, что ищем
        System.out.println("Search Word: " + wordToFind );
        int count = TaskForWorks.countOccurance(list, wordToFind);
        System.out.println("Antwort: " + count);
        System.out.println("__________________________");
        System.out.println("2. toList");
        int[] array = {1, 3, 6, 7 ,8 , 9, 11};
        List<Integer> converToList = TaskForWorks.toList(array);
        System.out.println(converToList);
        System.out.println("__________________________");
        System.out.println("3. findUnique");
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 3, 5, 6, 6, 7, 6, 7, 8, 8, 3));
        System.out.println(list1);
        List<Integer> uniqueList = TaskForWorks.findUnique(list1);
        System.out.println(uniqueList);
        System.out.println("__________________________");
        System.out.println("4. calcOccurance");
        List<String> animals = new ArrayList<>(Arrays.asList( "cat", "dog", "bird", "cat", "dog",
                "fox", "lion", "tiger", "fox", "bear",
                "cat", "wolf", "dog", "lion", "wolf"));
        System.out.println(animals);
        TaskForWorks.calcOccurance(animals);
        System.out.println("__________________________");
        System.out.println("5*. findOccurance");
        List<String> animals2 = new ArrayList<>(Arrays.asList( "cat", "dog", "bird", "cat", "dog",
                "fox", "lion", "tiger", "fox", "bear",
                "cat", "wolf", "dog", "lion", "wolf"));
        List<WordStat> start = TaskForWorks.findOccurance(animals2);
        System.out.println(start);
        System.out.println("______________________________  2  _________________________________");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(new Entry("Anna", "123-456"));
        phoneBook.add(new Entry("Ivan", "789-101"));
        phoneBook.add(new Entry("Anna", "999-888"));
        phoneBook.add(new Entry("Maria", "111-222"));

        phoneBook.printFind("Anna");
        phoneBook.printFind("Petr");
        System.out.println("__________________________");
        phoneBook.printFindAll("Anna");
        phoneBook.printFindAll("Petr");

    }
}