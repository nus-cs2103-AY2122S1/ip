public class Event extends Task{
    protected String during;

    public Event(String description, String during) {
        super(description);
        this.during = during;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + during + ")";
    }
}
