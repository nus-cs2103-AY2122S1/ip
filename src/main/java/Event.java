public class Event extends Task{
    protected String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.dateTime + ")";
    }
}
