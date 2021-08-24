public class Event extends Task{
    protected String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Event(String description, String dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.dateTime;
    }

    @Override
    public String getTaskIdentifier() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.dateTime + ")";
    }
}
