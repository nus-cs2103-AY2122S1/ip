public class Event extends Task {

    private final String identifier = "E";
    private String interval;

    public Event(String description, String interval) {
        super(description);
        this.interval = interval;
    }

    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += " (at: " + this.interval + ")";
        return result;
    }

    @Override
    public String databaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|" + this.interval;
        return result;
    }
}
