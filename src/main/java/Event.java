public class Event extends Task {
    private final String dateTime;
    public Event(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime);
    }
}
