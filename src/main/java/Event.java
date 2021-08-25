import java.io.IOException;

public class Event extends Task {
    protected String at;
    private static final String label = "E";

    public Event(String description, String at) throws DukeException, IOException {
        super(description);
        if (at.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + label + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDataString() {
        return label + super.toDataString() + " | " + at;
    }
}


