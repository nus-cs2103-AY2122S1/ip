public class Event extends Task {

    protected String at;
    protected String isDone;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String isDone, String description, String at) {
        super(description, isDone.equals("1"));
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + at;
    }
}