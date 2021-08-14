public class Event extends Task {

    protected String startTime;

    public Event(String description, String startTime) {
        super(description);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + startTime + ")";
    }
}
