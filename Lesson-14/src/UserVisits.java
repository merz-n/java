import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class UserVisits {
    private String name;
    private List<LocalTime> timeList;

    public UserVisits(String name) {
        this.name = name;
        this.timeList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<LocalTime> getTimeList() {
        return timeList;
    }

    public void add(LocalTime time){
        timeList.add(time);
    }
}
