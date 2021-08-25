public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public Event(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return "E," + super.toFileData();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
