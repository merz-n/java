import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Camp {

    private List<Boyscout> boyscouts;

    public Camp(List<Boyscout> boyscouts) {
        this.boyscouts = boyscouts;
    }
    public Map<Team, List<Boyscout>> split(){
        Map<Team, List<Boyscout>> group = boyscouts.stream()
                .collect(Collectors.groupingBy(Boyscout::getTeam));
        for (Map.Entry<Team,List<Boyscout>> entry: group.entrySet()){
    List<Boyscout> list = entry.getValue();
    list.sort(Comparator.comparing(Boyscout::getAge).reversed());
        }
        return group;
    }
}
