import java.time.LocalTime;
import java.util.*;

public class AttendanceLogger {
    private Map<String, UserVisits> visitByUser;

    public AttendanceLogger(){
        this.visitByUser = new HashMap<>();
    }
    public void registerVisit(String userID, String timeList){
        LocalTime time = LocalTime.parse(timeList);
        if(!visitByUser.containsKey(userID)){
            visitByUser.put(userID,new UserVisits(userID));
        }
        visitByUser.get(userID).add(time);
    }

    public Map<String,Integer> getVisitFrequencies(){
        Map<String, Integer> result= new HashMap<>();
        for(Map.Entry<String,UserVisits> entry : visitByUser.entrySet()){
            String UserId = entry.getKey();
            UserVisits userVisits = entry.getValue();
            int count = userVisits.getTimeList().size();
            result.put(UserId,count);
        }
        return result;
    }
    public String getMostPopularHour(){
        Map<Integer,Integer> hourToCount = new HashMap<>();
        for(Map.Entry<String,UserVisits> entry : visitByUser.entrySet()){
          UserVisits visit = entry.getValue();
          for(LocalTime time  : visit.getTimeList()){
              int hour = time.getHour();
              hourToCount.put(hour, hourToCount.getOrDefault(hour, 0) + 1);
          }

        }
        int mostPopularHour = -1;
        int maxCount = -1;
        for(Map.Entry<Integer,Integer> entry : hourToCount.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                mostPopularHour = entry.getKey();

            }
        }
        return String.format("%02d:00",mostPopularHour);
    }

}
