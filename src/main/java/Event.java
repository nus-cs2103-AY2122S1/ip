public class Event extends Task{
    protected String by;
    public Event(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + by + ")";
    }
}
