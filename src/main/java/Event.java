public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the event.");
        }
        if (at == "") {
            throw new DukeException("Looks like you forgot to include when the event is at.");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDataString(String delimiter) {
        String tag = "E";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description, at);
    }
}
