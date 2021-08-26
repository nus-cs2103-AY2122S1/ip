public class Event extends Task {
    private String TYPE = "Event";
    private String eventTime;

    public static Event of(String taskSummary, String eventTime) {
        return new Event(taskSummary, eventTime);
    }

    public Event(String taskSummary, String eventTime) {
        super(taskSummary);
        this.eventTime = eventTime;
    }

    public static String syntax() {
        return "event command syntax: \'event <task> /at <eventTime>\'";
    }

    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            this.taskTypeSymbol(), this.isCompleted() ? 1 : 0, this.getTaskSummary(), this.eventTime
        );
    }

    private String taskTypeSymbol() {
        return Character.toString(this.TYPE.charAt(0));
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (at: %s)",
            this.taskTypeSymbol(),
            this.isCompleted() ? "X" : "",
            this.getTaskSummary(),
            this.eventTime
        );
    }
}
