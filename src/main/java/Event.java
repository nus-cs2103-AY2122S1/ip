public class Event extends Task {
    String at;
    public Event(String desc, String at) throws DukeException.EmptyDescriptionException {
        super(desc);
        this.at = at;
    }

    public Event(boolean isComplete, String desc, String at) throws DukeException.EmptyDescriptionException {
        super(isComplete, desc);
        this.at = at;
    }

    @Override
    public String getRepr() {
        return String.format("E|%s|%s", super.getRepr(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
