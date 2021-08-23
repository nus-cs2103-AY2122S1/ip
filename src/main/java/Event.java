public class Event extends Task {
    protected String description;
    protected boolean isDone;
    final String EVENT = "[E]";
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String getStatusAndDescription() {return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: "
            + this.date + ")"; }
}
