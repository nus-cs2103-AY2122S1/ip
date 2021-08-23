public class Event extends DateTask {
    public Event(String description, String at) throws DukeException {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate() + ")";
    }
}
