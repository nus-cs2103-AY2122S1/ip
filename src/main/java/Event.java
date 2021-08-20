public class Event extends Task {
    private String type;
    private String eventTime;

    public static Event of(String taskSummary, String eventTime) {
        return new Event(taskSummary, eventTime);
    }

    public Event(String taskSummary, String eventTime) {
        super(taskSummary);
        this.type = "Event";
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (at: %s)",
                this.type.charAt(0),
                this.isCompleted() ? "X" : "",
                this.getTaskSummary(),
                this.eventTime
        );
    }
}
