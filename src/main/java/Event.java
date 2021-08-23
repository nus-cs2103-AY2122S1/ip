public class Event extends Task {
    private final String dateTime;

    public Event(boolean done, String taskName, String dateTime) {
        super(done, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        return String.format("E|%s|%s", super.encode(), dateTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime);
    }
}
