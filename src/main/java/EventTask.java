public class EventTask extends Task {

    public String eventDuration;

    public EventTask(String description, String eventDuration) {
        super(description);
        this.eventDuration = eventDuration;
    }

    @Override
    public String getTaskStatus() {
        return "[E]" + super.getTaskStatus();
    }

    @Override
    public String toString() {
        return super.toString() + " " + "(at: " + this.eventDuration + ")";
    }
}
