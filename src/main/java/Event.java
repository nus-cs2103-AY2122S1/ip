public class Event extends Task {
    private final Datetime eventDatetime;

    public Event(String taskName, String eventDatetime) {
        super(taskName);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDatetime.toString() + ")";
    }
}
