package phonebook;
import java.util.*;

public class PhoneBook {
    List<Entry> entries = new ArrayList<>();

    public void add(Entry entry) {
        entries.add(entry);
    }
    public Entry find(String name) {
        for (Entry entry : entries){
            if(entry.getName().equalsIgnoreCase(name)){
                return entry;
            }
        }
        return null;
    }
    public List<Entry> findAll(String name) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries){
            if(entry.getName().equalsIgnoreCase(name)){
                result.add(entry);
            }
        }
        return result;
    }

    public void printFind(String name){
        Entry result = find(name);
        if(result != null){
            System.out.println("First entry for: " + name + " - " + result);
        }else{
            System.out.println("Not find entry for: " + name );
        }
    }
    public void printFindAll(String name){
        List<Entry> result = findAll(name);
        if(result.isEmpty()){
            System.out.println("Not find entry for: " + name );

        }else{
            System.out.println("All entry for: " + name);
            for(Entry e : result){
                System.out.println( e);
            }
        }
    }
}
