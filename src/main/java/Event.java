public class Event extends Task {
    private final Datetime eventDatetime;
    private static final String TASK_TAG = "event";

    public Event(String taskName, String eventDatetime) {
        super(taskName);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    public Event(String taskName, String eventDatetime, boolean isDone) {
        super(taskName, isDone);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    public String fileSaveFormat() {
        return String.format("E | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(),
                this.eventDatetime.getDatetimeString());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDatetime.toString() + ")";
    }
}
