public class Event extends Task {

    protected String startEnd;

    public Event(String description, String startEnd) {
        super(description);
        this.startEnd = startEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startEnd + ")";
    }
}
