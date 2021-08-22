import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    private String at;
    private static final char TASK_LETTER = 'E';

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", Event.TASK_LETTER, super.toString(), this.at);
    }

    @Override
    public String stringToStore() {
        return Event.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.at + "\n";
    }
}
