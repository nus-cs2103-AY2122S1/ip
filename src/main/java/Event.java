public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String descAndTime) {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    private static String extractDesc(String descAndTime) {
        return descAndTime.split("at")[0];
    }

    private static String extractTime(String descAndTime) {
        return descAndTime.split("at")[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
