package duke;

public class Event extends DateTask {
    public Event(String description, String at) throws DukeException {
        this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description, at, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate() + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + getDateString();
    }
}
