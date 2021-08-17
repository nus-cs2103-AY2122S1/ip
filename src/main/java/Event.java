public class Event extends Task {
    public Event(String description, String time) {
        super(String.format("%s (at: %s)", description, time));
    }

    public String getTaskIcon() {
        return "E";
    }
}
