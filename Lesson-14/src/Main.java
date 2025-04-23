import java.util.Arrays;
import java.util.List;
import java.util.Map;



public class Main {

    public static void main(String[] args) {
        AttendanceLogger logger = new AttendanceLogger();
        List<String[]> logs = Arrays.asList(
                new String[]{"user1", "09:15"},
                new String[]{"user2", "10:00"},
                new String[]{"user1", "09:45"},
                new String[]{"user3", "11:30"},
                new String[]{"user2", "10:20"},
                new String[]{"user4", "14:10"},
                new String[]{"user1", "09:55"},
                new String[]{"user2", "10:45"},
                new String[]{"user4", "14:25"},
                new String[]{"user3", "11:50"},
                new String[]{"user5", "15:00"},
                new String[]{"user1", "16:30"},
                new String[]{"user3", "11:59"}
        );
        for (String[] log : logs){
            String userID = log[0];
            String time = log[1];
            logger.registerVisit(userID,time);
        }
        System.out.println("________________________________");
        System.out.println("getVisitFrequencies: ");
        Map<String, Integer> free = logger.getVisitFrequencies();
        for (Map.Entry<String, Integer> entry : free.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " time's");
        }
        System.out.println("________________________________");
        System.out.println("MostPopularHour: "  + logger.getMostPopularHour());
    }
}