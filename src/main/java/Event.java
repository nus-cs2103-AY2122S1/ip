public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public Event(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return "E," + super.toFileData();
    }
    public Event(String description, String date) throws DukeException {
        super(description, date);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), super.getDateString());
    }
}
