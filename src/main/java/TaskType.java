public enum TaskType {
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E");

    private final String label;
    private final String abbr;

    TaskType(String label, String abbr) {
        this.label = label;
        this.abbr = abbr;
    }

    public String getLabel() {
        return label;
    }

    public String getAbbr() {
        return abbr;
    }
}
