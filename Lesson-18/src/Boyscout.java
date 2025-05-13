public class Boyscout {
    private String name;
    private Integer age;
    private Team team;

    public Boyscout(String name, Integer age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Boyscout{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
