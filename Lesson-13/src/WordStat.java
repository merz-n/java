public class WordStat {
    private String name;
    private int occurence;

    public WordStat(String name, int occurencer) {
        this.name = name;
        this.occurence = occurencer;
    }

    public String getName() {
        return name;
    }

    public int getOccurencer() {
        return occurence;
    }

    @Override
    public String toString() {
        return "{name: \"" + name + "\", occurrence: " + occurence + "}";
    }
}
