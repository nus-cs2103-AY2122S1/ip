public class Event extends Task {
    private final Datetime eventDatetime;

    public Event(String taskName, String eventDatetime) {
        super(taskName);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    public Event(String taskName, String when, boolean isDone) {
        super(taskName, isDone);
        this.when = when;
    }

    public String fileSaveFormat() {
        return String.format("E | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(), this.when);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDatetime.toString() + ")";
    }
}
