public class Event extends Task{
    protected String description;
    protected boolean isDone;
    final String EVENT = "[E]";
    protected String event;

    public Event(String description, String event) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }

    @Override
    public String getStatusAndDescription() {return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + this.event + ")"; }
}
