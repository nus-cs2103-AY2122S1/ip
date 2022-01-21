public class Event extends Task {

    protected String at;

    public Event(String description, String by) {
        super(description);
        this.at = by;
    }

    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
