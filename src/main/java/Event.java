public class Event extends Task {
    protected String timeStart;

    public Event(String description, String timeStart) {
        super(description);
        this.timeStart = timeStart;
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[E]" + description + " (at: " + this.timeStart + ")";
    }
}
